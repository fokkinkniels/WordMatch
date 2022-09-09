# WordMatch

Dit project heeft betrekking op mijn individueel project voor semester 3 bij de Fontys. in dit project zal al mijn documentatie, code en eventueel andere benodigdheden staan voor mijn project. 


## Inhoud

- [Introductie](#introductie)
  - [Wie ben ik](#wie-ben-ik)
  - [Het Idee](#het-idee)
  - [Meer Details](#meer-details)
- [User Stories](#user-stories)
  - [De Users](#de-users)
  - [De User Stories](#de-user-stories)


## Introductie

### Wie ben ik

Mijn naam is Niels, ik studeer ICT aan de fontys in Eindhoven. Ik ben 21 jaar oud en kom uit het prachtige Berghem. In mijn vrije tijd game ik graag met vrienden, vind ik het leuk om op stap of naar festivals te gaan en ik vind het heel leuk om dingen te maken, zowel op ICT-gebied als in met m'n handen.  

### Het idee

Ik speel soms met mijn zusje een spelletje genaamd [Word Wizard](https://www.megableu.com/regles-jeu/woordwizard-flashbac.pdf), in dit spel draait het erom wie de meest originele/creatieve woorden kan bedenken binnen een categorie met een specifieke begin letter. Een persoon dobbelt en mag een categorie uitkiezen, daarna mogen de spelers naast hem/haar een categorie kiezen. Als iedereen een categorie heeft wordt er een letter omgedraaid en heeft iedereen 90 seconden om 3 woorden voor zijn/haar eigen categorie op te schrijven en 2 woorden per tegenspeler van hun categorie. Daarna worden alle woorden met elkaar vergeleken en krijgt iedereen een punt per origineel woord dat ze hebben. Als speler 1 dus een woord van speler 2 heeft geraden krijgt speler 1 geen punt hiervoor. Wie het eerst 15 punten heeft heeft het spel gewonnen. Dit spel wil ik in een app maken zodat het altijd en overal gespeeld kan worden. 

### Meer details

Ik wil een app gaan maken om het spel woord wizard te spelen op je telefoon met vrienden. Woord wizard is een bordspel waarbij je kennis over onderwerpen wordt getest door binnen een bepaalde tijd een 3 woorden binnen een categorie met een specifieke begin letter op te schrijven terwijl je je tegenspeelt probeert dwars te liggen door hun woorden te raden. 

Ik wil de app speelbaar maken vanaf een apparaat of vanaf meerdere, hiervoor komt een optie aan het begin van het spel. Om op meerdere apparaten te spelen moeten alle spelers een account hebben en een eigen apparaat met de app. Om de game met meerdere apparaten te spelen is er een internetverbinding nodig om met elkaar te communiceren. Als de game op een apparaat gespeeld wordt is dit offline dus mogelijk vanaf overal. 

De app kan speler profielen onthouden en opslaan voor gebruik in latere games of om scores en games op te slaan zodat je kan zien wie er van je vrienden het beste is. Hiermee zou er ook een soort highscore pagina kunnen komen of een highlight pagina met de beste, meest bizarre of oneerlijkste overwinningen.  

Je hebt geen profiel nodig om online met vrienden te kunnen spelen, je kunt de game met een gast-account spelen waardoor je geen account aan hoeft te maken, echter worden je scores en games dan niet opgeslagen. Als je wel een account hebt kun je ook de accounts van je vrienden toevoegen zodat het makkelijker is om samen een spel te spelen. 

De app krijgt een verificatie feature om te checken of de ingevulde antwoorden wel binnen de categorie vallen en met de goede letter beginnen. Bijvoorbeeld: de categorie is sporten en de letter is een B, een speler geeft als woord Basketbal. De game checkt dan of Basketbal met een B begint, zo ja kijkt de game of basketbal een sport is, als dit niet zo is zal er een opmerking bij komen te staan van “klopt dit?”, want het is natuurlijk mogelijk dat de persoon wel iets weet maar de game dit niet herkent of dat er een spelfout in het antwoord staat. 


## User Stories

### De Users

#### Karel Apenboom

Karel is een jongen van 21 die in zijn vrije tijd graag gamet met zijn vrienden en is hier soms best competitief in. Hij is niet de hardcore gamer die elke avond tot 4 uur achter zijn PC zit maar als het weekend is en hij heeft niks de volgende dag wordt het al gauw na twaalven. Sommige van zijn vrienden zijn wel de hardcore gamers die elk vrij moment opgeven om nog een spelletje te spelen, die jongens zijn dus ook een stuk beter dan hem omdat ze meer uren in de spellen hebben. Karel vindt het daarom leuk om spelletjes met zijn vrienden te spelen waar het niet uitmaakt hoe goed je met je muis en keyboard bent maar waar iedereen gelijk in is ondanks hoeveel deze persoon gespeeld heeft. Karel vindt het ook leuk om met zijn vrienden af te spreken en een biertje te drinken en een spelletje te spelen. Karel werkt elke weekend een paar uur in de supermarkt als groenteboer en doet dit al sinds dat hij 17 is. 


### De user stories
Dit zijn de user stories van mijn project op volgorde van prioriteit. Onder de user stories staan de gerelateerde acceptatiecriteria:


- **U1** Karel wil een makkelijk en overzichtelijk interface zodat het spel makkelijk is uit te leggen aan zijn vrienden dit het nog nooit gespeeld hebben. 
  - Er moet een UI gemaakt worden
  - De regels moeten uitgelegd worden
  - De UI moet getest zijn om aan te tonen dat hij overzichtelijk is


- **U2** Karel en zijn vrienden willen het spel vanaf hun eigen telefoon spelen zodat iedereen gelijke kansen heeft, niemand vals kan spelen en de scores en games opgeslagen kunnen worden.
    - Er moeten twee of meerdere applicaties met elkaar verbonden kunnen worden


- **U3** Karel wil kunnen zien wie er het meeste punten heeft verdient van hem en zijn vrienden zodat hij kan zien wie er gewonnen heeft. 
    - Er moet een pagina zijn waar alle behaalde scores op tezien zijn


- **U4** Karel wil een feature die antwoorden nakijkt zodat er geen foute antwoorden goed gerekend kunnen worden. 
    - Als er een fout antwoord ingevuld word moet er een 'red flag' bij komen te staan
    - Er moet een database zijn met correcte antwoorden
    - Er moeten woorden aan de database toegevoegd kunnen worden



## Requirements

### U1

  - [x] Flowchart van het spel maken
  - [x] Regels uitschrijven
  - [x] Flowchart applicatie maken
  - [ ] Homepage ontwerpen
  - [ ] Regel pagina ontwerpen
  - [ ] Spel pagina's ontwerpen
  - [ ] Score pagina ontwerpen
  - [ ] User test maken
  - [ ] Design testen
