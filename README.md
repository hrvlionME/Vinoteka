# Vinoteka

Projekt za kolegij "Programiranje u Javi"


## Docker-compose
Za pokretanje Docker-skripte potrebno je upaliti Docker Desktop i u BASH-u unutar foldera ukucati:

> docker compose build
> docker compose up -d

Unutar scripts foldera se nalazi schema koja ubacuje admina unutar aplikacije.

> **Login za admina:**
> Mail: admin@test.com
> Lozinka: lozinka


## Rute
**Dostupne svima**
"/" - homepage
"/auth/login/" - login
"/auth/register/" - register

**Dostupne isključivo gostima**
"/showcase" - pregled vina za goste

**Dostupne korisnicima**
"/store" - pregled vina za korisnike

**Dostupne administratoru**
"/edit" - pregled vina za administratora
"/edit/add" - forma za ubacivanje novih vina