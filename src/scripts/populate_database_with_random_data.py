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

TODO: found a better api https://mockaroo.com/. I will update the script later or make another version.
TODO: update script to populate other tables (Appointment ...) with random data

Tested with python 3.5.
Requires requests package (pip install requests)
Tested on commit: 4a4c6e4e2c159790dce80b57c619d4aef9e4343c
"""

import requests
import json


def api_request(nr_of_users):
    """

    :param nr_of_users:
    :return:
    """

    return requests.get("https://randomuser.me/api/?results={0}&nat=au,us,gb,br".format(nr_of_users)).json()


def build_person(api_json):
    """

    :param api_json:
    :return:
    """

    person_json = dict()

    phone_number = dict({"phone_number": "0700000000"})
    email = dict({"email": api_json["email"]})
    address = dict({"street": api_json["location"]["street"],
                    "county": None,
                    "city": api_json["location"]["city"],
                    "state": api_json["location"]["state"],
                    "postal_code": api_json["location"]["postcode"],
                    "country": None})

    person_json["firstName"] = api_json["name"]["first"]
    person_json["lastName"] = api_json["name"]["last"]
    person_json["phoneNumber"] = phone_number
    person_json["email"] = email
    person_json["address"] = address

    return person_json


def build_doctor(person_json, api_json):
    """

    :param person_json:
    :param api_json:
    :return:
    """

    person_json["function"] = "placeholder"
    return person_json


def build_patient(person_json, api_json):
    """

    :param person_json:
    :param api_json:
    :return:
    """

    person_json["age"] = 10
    return person_json


def parse_json(api_json, size):
    """

    :param api_json:
    :param size:
    :return:
    """

    doctors = list()
    patients = list()

    print(size)
    for idx in range(0, int(size/2)):
        person_json = build_person(api_json['results'][idx])
        doctors.append(build_doctor(person_json, api_json))

    for idx in range(int(size / 2), size):
        person_json = build_person(api_json['results'][idx])
        patients.append(build_doctor(person_json, api_json))

    return doctors, patients


def post_request(request_link, data, headers):
    """

    :param request_link:
    :param data:
    :param headers:
    :return:
    """

    r = requests.post(request_link, data=json.dumps(data), headers=headers)
    print(r.status_code)


def post_list(request_link, data_list, headers):
    """

    :param request_link:
    :param data_list:
    :param headers:
    :return:
    """

    for element in data_list:
        post_request(request_link, element, headers)


MAX_ITEMS = 1000

current_items = 1000
if __name__ == "__main__":

    if current_items <= MAX_ITEMS:
        random_api_json = api_request(current_items)
        parsed_doctor_list, parsed_patient_list = parse_json(random_api_json, current_items)
        req_header = {'content-type': 'application/json', 'Accept': 'application/json'}
        post_list("http://localhost:8080/api/0.1/doctors", parsed_doctor_list, req_header)
        post_list("http://localhost:8080/api/0.1/patients", parsed_patient_list, req_header)
    else:
        print("Please insert a lower number")
