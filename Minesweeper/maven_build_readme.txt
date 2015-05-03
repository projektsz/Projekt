Hogyan fordítsuk a projektet Maven-nel?

1. Telepítés

- Töltsd le és csomagold ki a Maven-t tetszőleges mappába: http://maven.apache.org/download.cgi
- A kicsomagolt mappában található bin könyvtárat add hozzá a PATH-hez.
- Hozz létre egy új környezeti változót JAVA_HOME néven. Értékének add meg a JDK elérési útját. (Itt most ne a bin mappáét)
- Teszteléshez add ki az alábbi parancssori parancsot: mvn -v 
	-Ha megjelenik a programról egy rövid információ akkor minden működik
	
2. Fordítás

- Nyisd meg a parancssor a Minesweeper mappában:
	- Ha csak a class fájlokat szeretnéd lefordítani add ki az alábbi parancsot: mvn compile
	- Ha csak a junit teszteket szeretnéd futtatni: mvn test
	- Ha jar fájlt szeretnél generálni: mvn package
- Sikeres fordítás után a fájlok a target mappában találhatóak

3. Javadoc

- mvn javadoc:javadoc
	- A target mappán belül az apidocs mappába generálja a javadoc-ot
- mvn javadoc:jar
	- Csinál egy olyan jar file-t ami tartalmazza a javadoc-ot is