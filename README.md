# Smart Appointment and Queue Management System

**Studentja:** Besjana Kurtaj  
**Lënda:** Test Driven Development (TDD) — Java 3  
**Repository:** https://github.com/besjana2/Smart-Appointment-Queue-TDD

## Përshkrimi
Projekt individual për rezervim dhe anulim të termineve dhe gjenerim të numrave të radhës për klinika, sportele dhe qendra shërbimi. Versioni aktual është logjikë në memorie, pa databazë dhe pa ndërfaqe grafike.

## Funksionalitetet
- Rezervim i terminit vetëm kur orari është i lirë; validim i orarit (`yyyy-MM-dd HH:mm`), emrit (2–50 karaktere), orëve 08:00–17:30 dhe intervalit 30-minutësh.
- Anulim i rezervimeve ekzistuese; për rezervime të panjohura kthehet `false`.
- Numra unikë të radhës duke filluar nga 1; kufi maksimal i konfigurueshëm (100 si parazgjedhje).
- Kontroll i rezervimeve dhe numërim i termineve aktive.

## Teknologjitë
Java 17, Maven, JUnit 5 (Jupiter, @ParameterizedTest).

## Ekzekutimi
Kërkohet JDK 17+ dhe Maven 3.8+.
```bash
mvn clean test
```
Rezultatet e ekzekutimit gjenden te `target/surefire-reports/`.

## Testimi i javës së tretë
Testet përfshijnë raste pozitive/negative, vlera kufitare, degë `if/else`, dhe teste të parametrizuara me `@ValueSource`/`@CsvSource`. Shihni `src/test/java/com/besjana/appointment/AppointmentServiceTest.java`.

## TDD / RED–GREEN–REFACTOR
Shihni `docs/TDD_Evidence_Instructions.md` për hapa konkretë të demonstrimit. **Commit-et RED/GREEN/REFACTOR duhet të realizohen vërtet në GitHub**; raporti nuk pretendon se commit-et janë kryer.

## Struktura
- `src/main/java/.../AppointmentService.java`: logjika kryesore
- `src/test/java/.../AppointmentServiceTest.java`: testet JUnit 5
- `docs/`: raporti i javës së tretë dhe udhëzimet TDD
