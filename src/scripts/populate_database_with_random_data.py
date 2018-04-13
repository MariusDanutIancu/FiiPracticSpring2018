"""
Author: Marius-Danut Iancu
Email:  marius.danut94@outlook.com
Creation date (mm-dd-yyyy): 4/10/2018
Version: 0.1
Status: Production

A. I. CUZA UNIVERSITY OF IAŞI
Faculty of Computer Science Iasi

Python script that does api requests to https://randomuser.me
and http://localhost:8080/api/0.1/ to generate random user data
to populate a database.

Tested with python 3.5.
Requires requests package (pip install requests)
"""

import logging
import requests
import json
import random
import time
from datetime import datetime, timedelta


def random_date(start, end, format, prop):
    """

    :param start:
    :param end:
    :param format:
    :param prop:
    :return:
    """

    stime = time.mktime(time.strptime(start, format))
    etime = time.mktime(time.strptime(end, format))
    ptime = stime + prop * (etime - stime)
    return time.strftime(format, time.localtime(ptime))


def add_hours(date, hours):
    """

    :param date:
    :param hours:
    :return:
    """

    return (datetime.strptime(date, DATE_FORMAT) + timedelta(hours=hours)).strftime(DATE_FORMAT)


def convert_date_string_to_iso(date):
    """

    :param date:
    :return:
    """

    return datetime.strptime(date, DATE_FORMAT).isoformat()


def api_request(nr_of_users):
    """

    :param nr_of_users:
    :return:
    """

    return requests.get("https://randomuser.me/api/?results={0}&nat=au,us,gb,br".format(nr_of_users)).json()


def get_phone_number(api_json):
    """

    :param api_json:
    :return:
    """

    phone_number = {
        "phone_number": "0700000000"
    }
    return phone_number


def get_email(api_json):
    """

    :param api_json:
    :return:
    """

    email = {
        "email": api_json["email"]
    }
    return email


def get_address(api_json):
    """

    :param api_json:
    :return:
    """

    # api_json["location"]["street"]
    # api_json["location"]["state"]
    # api_json["location"]["city"]
    address = {
        "street": "place_holder",
        "county": "place_holder",
        "city": "place_holder",
        "state": "place_holder",
        "postal_code": api_json["location"]["postcode"],
        "country": "place_holder"
    }
    return address


def get_person(api_json):
    """

    :param api_json:
    :return:
    """

    person = {
        "firstName": api_json["name"]["first"],
        "lastName": api_json["name"]["last"],
        "address": get_address(api_json),
        "email": get_email(api_json),
        "phoneNumber": get_phone_number(api_json)
    }

    return person


def get_doctor(person_json, api_json):
    """

    :param person_json:
    :param api_json:
    :return:
    """

    person_json["function"] = "placeholder"
    return person_json


def get_patient(person_json, api_json):
    """

    :param person_json:
    :param api_json:
    :return:
    """

    person_json["age"] = random.randint(MIN_AGE, MAX_AGE)
    return person_json


def get_appointment(max_doctor_id, max_patient_id):

    start_time = random_date(MIN_DATE, MAX_DATE, DATE_FORMAT, random.random())
    end_time = add_hours(start_time, random.randint(1, 3))

    appointment_json = {
        "doctor_id": random.randint(1, max_doctor_id + 1),
        "patient_id": random.randint(1, max_patient_id + 1),
        "startTime": convert_date_string_to_iso(start_time),
        "endTime": convert_date_string_to_iso(end_time),
        "cause": "placeholder"
    }

    return appointment_json


def appointment_generator(max_doctor_id, max_patient_id, count):
    """

    :param max_doctor_id:
    :param max_patient_id:
    :param count:
    :return:
    """

    for _ in range(count):
        yield get_appointment(max_doctor_id, max_patient_id)


def post_request(request_link, data, headers):
    """

    :param request_link:
    :param data:
    :param headers:
    :return:
    """

    r = requests.post(request_link, data=json.dumps(data), headers=headers)
    if r.status_code == 500:
        print(r.json())
        print(data)


def post_elements(parse_function, data, request_link, headers):
    """

    :param parse_function:
    :param data:
    :param request_link:
    :param headers:
    :return:
    """

    for element in data:
        post_request(request_link, parse_function(element), headers)


def post_elements_by_generator(data_generator, request_link, headers):
    """

    :param count:
    :param data_generator:
    :param request_link:
    :param headers:
    :return:
    """

    try:
        while True:
            post_request(request_link, next(data_generator), headers)
    except StopIteration:
        pass


def config_logger():

    logger = logging.getLogger()
    handler = logging.StreamHandler()
    formatter = logging.Formatter('%(asctime)s %(name)-3s %(levelname)-3s %(message)s')
    handler.setFormatter(formatter)
    logger.addHandler(handler)
    logger.setLevel(logging.INFO)

    return logger


MAX_ITEMS = 1000
MIN_AGE = 1
MAX_AGE = 150
MIN_DATE = "2000-01-01 12:00:00"
MAX_DATE = "2025-01-01 12:00:00"
DATE_FORMAT = "%Y-%m-%d %H:%M:%S"
MAX_APPOINTMENTS = 2000

POST_REQUEST_LINK_TEMPLATE = "http://localhost:8080/api/0.1/{}"
REQUEST_HEADER = {'content-type': 'application/json', 'Accept': 'application/json'}

current_items = 1000
if __name__ == "__main__":

    logger = config_logger()

    if current_items <= MAX_ITEMS:

        api_result = api_request(current_items)["results"]
        doctors = api_result[0: current_items // 2]
        patients = api_result[current_items // 2:]

        post_elements(lambda x: get_doctor(get_person(x), doctors),
                      doctors,
                      POST_REQUEST_LINK_TEMPLATE.format("doctors"),
                      REQUEST_HEADER)
        logger.info("Doctors added")

        post_elements(lambda x: get_patient(get_person(x), patients),
                      patients,
                      POST_REQUEST_LINK_TEMPLATE.format("patients"),
                      REQUEST_HEADER)
        logger.info("Patients added")

        post_elements_by_generator(appointment_generator(current_items // 2, current_items // 2, MAX_APPOINTMENTS),
                                   POST_REQUEST_LINK_TEMPLATE.format("appointments"),
                                   REQUEST_HEADER)
        logger.info("Appointments added")

    else:
        logger.warning("Please insert a lower number")
