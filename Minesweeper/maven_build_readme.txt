Hogyan ford�tsuk a projektet Maven-nel?

1. Telep�t�s

- T�ltsd le �s csomagold ki a Maven-t tetsz�leges mapp�ba: http://maven.apache.org/download.cgi
- A kicsomagolt mapp�ban tal�lhat� bin k�nyvt�rat add hozz� a PATH-hez.
- Hozz l�tre egy �j k�rnyezeti v�ltoz�t JAVA_HOME n�ven. �rt�k�nek add meg a JDK el�r�si �tj�t. (Itt most ne a bin mapp��t)
- Tesztel�shez add ki az al�bbi parancssori parancsot: mvn -v 
	-Ha megjelenik a programr�l egy r�vid inform�ci� akkor minden m�k�dik
	
2. Ford�t�s

- Nyisd meg a parancssor a Minesweeper mapp�ban:
	- Ha csak a class f�jlokat szeretn�d leford�tani add ki az al�bbi parancsot: mvn compile
	- Ha csak a junit teszteket szeretn�d futtatni: mvn test
	- Ha jar f�jlt szeretn�l gener�lni: mvn package
- Sikeres ford�t�s ut�n a f�jlok a target mapp�ban tal�lhat�ak