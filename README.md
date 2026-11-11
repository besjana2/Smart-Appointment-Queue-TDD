# Smart Appointment and Queue Management System

**Studentja:** Besjana Kurtaj  
**Lënda:** Test Driven Development (TDD) — Java 1  
**Repository:** https://github.com/besjana2/Smart-Appointment-Queue-TDD

## Përshkrimi
Aplikacion për rezervimin dhe anulimin e termineve dhe për lëshimin e numrave të radhës, me synim uljen e pritjeve dhe shmangien e rezervimeve të dyfishta. Versioni i javës së parë është një shërbim i thjeshtë në memorie, jo sistem web i përfunduar.

## Qëllimi
Të organizojë në mënyrë më të qartë takimet dhe radhët për institucione, klinika, zyra shërbimi dhe klientët e tyre.

## Teknologjitë
- Java 17
- Maven
- JUnit 5 (junit-jupiter)

## Tri funksionalitetet dhe testet
1. **Rezervim termini:** rezervim i një orari të lirë; refuzim i orarit të zënë dhe emrit bosh.
2. **Anulim termini:** anulim i një rezervimi ekzistues; rezultat `false` nëse nuk gjendet.
3. **Numra radhe:** numri i parë është 1; çdo kërkesë e re e rrit numrin.

Ka **7 teste JUnit 5** në `src/test/java/com/besjana/appointment/AppointmentServiceTest.java`.

## Si të ekzekutohen
Instaloni JDK 17+ dhe Maven 3.9+; nga rrënja e projektit:

```bash
mvn test
```

Maven gjeneron rezultatet në `target/surefire-reports/`. Bëni screenshot ose kopjoni përmbledhjen e testeve në raport pas ekzekutimit.

**Status:** Testet janë përgatitur, por nuk janë ekzekutuar në këtë mjedis; rezultati duhet verifikuar lokalisht.

## Metoda TDD
Për secilin rast: shkruaj testin që dështon (**Red**), implemento kodin minimal që e kalon (**Green**), dhe përmirëso strukturën pa ndryshuar sjelljen (**Refactor**).

## Struktura
- `pom.xml` — varësitë Maven / JUnit 5
- `src/main/java/.../AppointmentService.java` — funksionalitetet fillestare
- `src/test/java/.../AppointmentServiceTest.java` — testet
- `docs/` — raporti i javës së parë
