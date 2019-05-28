#include <DHT.h>
#include "DHT.h"
#define DHTPIN 7
#define DHTTYPE DHT11
DHT dht(DHTPIN, DHTTYPE);

void setup() {
  Serial.begin(9600);
}

void loop() {
  int t=dht.readTemperature();
  int h=dht.readHumidity();
  Serial.print("Temperatura: ");
  Serial.println(t);
  Serial.print("Umidita': ");
  Serial.print(h);
  Serial.println(" %");
  delay(2000);
}
