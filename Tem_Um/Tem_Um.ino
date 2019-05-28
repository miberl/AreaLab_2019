#include <DHT.h>
#include "DHT.h"
#define DHTPIN 5
#define DHTTYPE DHT11
int letturainput=8;
DHT dht(DHTPIN, DHTTYPE);

void setup() {
  digitalWrite(13,HIGH);
  Serial.begin(9600);

}

void loop() {
    int valoreletto=digitalRead(letturainput);
    if (valoreletto==1){
      int t=dht.readTemperature();
      int h=dht.readHumidity();
      Serial.print(" Temperatura: ");
      Serial.print(t);
      Serial.println("°C");
      Serial.print(" Umidita': ");
      Serial.print(h);
      Serial.println("%");
      Serial.println();
  }
  delay(2000);
  Serial.println(" Lettura");
  delay (1000);
  
}
