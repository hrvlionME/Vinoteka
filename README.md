# Vinoteka

Projekt za kolegij "Programiranje u Javi"

## Opis
Dobrodošli na našu vinoteku. Ova aplikacija pruža jednostavan, ali sotisficiran pristup svijetu vrhunskih domaćih vina, nudeći korisnicima jedinstvenu priliku da istraže, kupuju i uživaju u najfinijim vinima.

## Docker-compose
Za pokretanje Docker-skripte potrebno je upaliti Docker Desktop i u BASH-u unutar foldera ukucati:

> docker compose build <br/>
> docker compose up -d

Unutar scripts foldera se nalazi schema koja ubacuje admina unutar aplikacije.

> **Login za admina:** <br/>
> Mail: admin@test.com <br/>
> Lozinka: lozinka


## Rute
**Dostupne svima** <br/>
"/" - homepage <br/>
"/auth/login/" - login <br/>
"/auth/register/" - register

**Dostupne isključivo gostima** <br/>
"/showcase" - pregled vina za goste

**Dostupne korisnicima** <br/>
"/store" - pregled vina za korisnike

**Dostupne administratoru** <br/>
"/edit" - pregled vina za administratora <br/>
"/edit/add" - forma za ubacivanje novih vina

