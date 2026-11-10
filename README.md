# Smart Appointment and Queue Management System

**Studentja (projekt individual):** Besjana Kurtaj  
**Lënda:** Test Driven Development (TDD) — Java 1  
**Statusi:** Projekt fillestar / detyra e javës së parë

## Përshkrimi
Aplikacion për rezervimin dhe anulimin e termineve, shmangien e rezervimeve të dyfishta dhe menaxhimin e radhës së pritjes për një shërbim (p.sh. klinikë ose sportel).

## Qëllimi
Të zvogëlohet pritja dhe konfuzioni në menaxhimin e termineve përmes një sistemi të thjeshtë, të testueshëm dhe të zgjerueshëm.

## Teknologjitë e planifikuara
- **C# / .NET 8** për logjikën bazë
- **xUnit** për teste automatike (ekuivalent i JUnit 5 në ekosistemin .NET)
- **Git / GitHub** për versionim
- **ASP.NET Core** dhe një databazë (p.sh. SQLite) në fazat e ardhshme

**Vërejtje akademike:** Udhëzimi origjinal për javën 1 specifikon **JUnit 5 (Java)**. Ky repository përdor **xUnit (C#)** sipas teknologjisë së zgjedhur dhe kërkon miratimin e profesoreshës.

## Tri funksionalitetet e testuara
1. Rezervimi i terminit: termin i lirë dhe refuzim i rezervimit të dyfishtë.
2. Anulimi i terminit: anulim i suksesshëm dhe refuzim i ID-së së panjohur.
3. Menaxhimi i radhës: numra rendorë të njëpasnjëshëm dhe refuzim i emrit bosh.

## Struktura
```
Smart_Appointment_Queue_TDD/
├── README.md
├── .gitignore
├── src/SmartAppointmentQueue/SmartAppointmentQueue.csproj
├── src/SmartAppointmentQueue/AppointmentQueueService.cs
├── tests/SmartAppointmentQueue.Tests/SmartAppointmentQueue.Tests.csproj
├── tests/SmartAppointmentQueue.Tests/AppointmentQueueServiceTests.cs
└── docs/Raporti_Java_1.docx
```

## GitHub repository
Pas krijimit të repository-t në GitHub, vendosni këtu URL-në e tij. Emri i sugjeruar: `Smart-Appointment-Queue-TDD`.

## Ekzekutimi i testeve
Kërkohet .NET 8 SDK. Nga dosja kryesore:
```bash
dotnet test tests/SmartAppointmentQueue.Tests/SmartAppointmentQueue.Tests.csproj
```
Testet **nuk janë ekzekutuar në mjedisin e përgatitjes**; ekzekutojini lokalisht dhe shtoni screenshot-in e rezultatit në raport para dorëzimit.

## Metoda TDD
Red: shkruhen testet para implementimit → Green: implementim minimal për t'i kaluar → Refactor: përmirësim i kodit duke mbajtur testet funksionale.
