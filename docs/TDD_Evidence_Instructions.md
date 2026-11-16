# Dëshmia TDD (duhet të realizohet dhe dokumentohet nga studentja)

Shembull: refuzimi i një rezervimi jashtë orarit.

1. **RED:** shkruaj `booking_rejectsInvalidSlots` (p.sh. 07:30) në test; përpara validimit të orarit testi dështon. Ekzekuto `mvn clean test`; ruaj screenshot dhe bëj commit `test: reject appointments before opening hours (RED)`.
2. **GREEN:** implemento kontrollin e orës në `validateSlot`; ekzekuto `mvn clean test`; pasi testi kalon, bëj commit `feat: validate booking hours (GREEN)`.
3. **REFACTOR:** nxirr validimin në një metodë private `validateSlot`; ekzekuto sërish `mvn clean test`; bëj commit `refactor: centralize slot validation (REFACTOR)`.

Nëse kodi përfundimtar është ngarkuar si një commit, kjo nuk provon ciklin RED–GREEN–REFACTOR. Mund të përdorni screenshot-e nga hapat realë të punës ose commit-e të reja me ndryshime të tjera të vogla duke ndjekur të njëjtën metodë.
