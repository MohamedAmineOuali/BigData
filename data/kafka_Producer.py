import requests
from kafka import KafkaProducer
from time import sleep
import socket
from CONSTANTS import *

import logging
import pydevd

#enable logging
logging.basicConfig(level=logging.DEBUG)


def sendDataToKafka(data:str):

    producer.send(KAFKA_TOPIC, data)
    producer.flush()


producer = KafkaProducer(bootstrap_servers=KAFKA_ADDR, api_version=(0, 9), value_serializer=str.encode,
                         max_request_size=1000000000)
                         # max_partition_fetch_bytes=1100000000

while 1:
    data=input()
    sendDataToKafka(data)
