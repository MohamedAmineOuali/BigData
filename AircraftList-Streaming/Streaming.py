import requests
from kafka import KafkaProducer
from time import sleep

from CONSTANTS import *

import logging

#enable logging
logging.basicConfig(level=logging.DEBUG)


def sendDataToKafka(data:str):

    producer.send(KAFKA_TOPIC, data)
    producer.flush()


producer = KafkaProducer(bootstrap_servers=KAFKA_ADDR, api_version=(0, 9), value_serializer=str.encode,
                         max_request_size=1000000000)
                         # max_partition_fetch_bytes=1100000000

params = dict()

while True:
    resp = requests.get(url=AircraftList_url, params=params)
    data = resp.json()
    print('json received')
    sendDataToKafka(str(data))
    # sendDataToKafka('DONE')
    print('data sent to kafka')
    sleep(REQUEST_DELAY)