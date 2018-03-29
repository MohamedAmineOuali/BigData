AircraftList_url = 'https://public-api.adsbexchange.com/VirtualRadar/AircraftList.json'


KAFKA_HOST = '172.18.0.2'
KAFKA_PORT = 9092
KAFKA_ADDR = KAFKA_HOST + ':' + str(KAFKA_PORT)
KAFKA_TOPIC = 'Aircraft-Kafka-Topic'

#delai entre 2 requetes en minutes
REQUEST_DELAY=60