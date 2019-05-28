#include <DHT.h>
#include "DHT.h"
#define DHTPIN 5
#define DHTTYPE DHT11
int letturainput=8;
DHT dht(DHTPIN, DHTTYPE);
int val=-1;
void setup() {
  Serial.begin(9600);
  while (val==-1){
    val=Serial.read();
  }
  Serial.println(val);
}

void loop() {
    int valoreletto=digitalRead(letturainput);
    if (valoreletto==1){
        digitalWrite(3,HIGH);
        if (val==0){
          int t=dht.readTemperature();
          Serial.print(" Temperatura: ");
          Serial.print(t);
          Serial.println("°C");
          }
          
        if (val==1){
          int h=dht.readHumidity();
          Serial.print(" Umidita': ");
          Serial.print(h);
          Serial.println("%");
          }
        if (val==2){
          int t=dht.readTemperature();
          int h=dht.readHumidity();
          Serial.print(" Temperatura: ");
          Serial.print(t);
          Serial.println("°C");
          Serial.print(" Umidita': ");
          Serial.print(h);
          Serial.println("%");
      }
    }
        digitalWrite(3,LOW);
        delay(2000);
        Serial.println(" Lettura");
        delay (1000);
  }
