# 📚 Student Management System (Spring Boot + Docker)

Aceasta este o aplicație web de tip **MVC (Model-View-Controller)** pentru gestiunea studenților, profesorilor, cursurilor și notelor, implementată cu **Spring Boot**, **Thymeleaf**, **JPA** și **MySQL**, pregătită pentru rulare în containere **Docker**.

---

## 🧩 Tehnologii utilizate

- ✅ Java 17
- ✅ Spring Boot 3.1
    - Spring MVC
    - Spring Security
    - Spring Data JPA
- ✅ Hibernate
- ✅ Thymeleaf
- ✅ MySQL 8.x (în container)
- ✅ Docker & Docker Compose
- ✅ Maven (build)
- ✅ Bootstrap (interfață)

---

## ⚙️ Structura aplicației

- `controller/` – gestionarea rutelor și interacțiunea cu UI
- `model/` – entități JPA (tabele)
- `repository/` – operații CRUD + query-uri custom
- `service/` – logică de business
- `config/` – configurația securității
- `templates/` – fișiere HTML Thymeleaf
- `static/` – CSS, JS, imagini

---

## 🧠 Funcționalități principale

- Autentificare profesor (Spring Security)
- Adăugare profesor + curs asociat
- Adăugare student
- Atribuire cursuri/studenți unui profesor
- Adăugare notă pentru student
- Vizualizare notițe/cursuri/studenți
- Perspectivă simplă și responsive

---

## 🐳 Rulare cu Docker

### 🔄 Precondiții:
- Docker și Docker Compose instalate
- Porturile `3306` și `8080` libere

### ▶️ Comenzi:

1. Build & start:
```bash
docker-compose up --build
```

2. Accesează aplicația:
```
http://localhost:8080
```

3. Baza de date este persistentă într-un volum numit `student_data`.

---

## 🛠️ Cum funcționează auto-crearea bazei de date?

Spring Boot + Hibernate creează automat structura bazei de date pe baza claselor marcate cu `@Entity`, conform configurației din:

```properties
spring.jpa.hibernate.ddl-auto=update
```

Acest comportament se întâmplă automat la pornirea aplicației.

---

## 🔐 Utilizatori și securitate

Autentificarea este activată pentru zona de profesor. Poți crea un cont nou de profesor care implică și definirea unui curs asociat. Parolele sunt salvate în clar (pentru demo), dar pot fi criptate ușor cu `BCryptPasswordEncoder`.

---

## 🧪 Date de test

Fișierul `baza de date.sql` conține structură + câteva exemple. Însă aplicația creează automat tabelele necesare la rulare.

---

## 🧾 Autor

Proiect dezvoltat în scop educațional pentru demonstrarea unui stack complet backend+frontend cu Spring Boot și Docker.

---

## 📄 Licență

Folosire liberă pentru studiu și proiecte personale.
