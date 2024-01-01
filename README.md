Erweitung dieses Beispiel.
- [x] GUI 
- [] Wechselkurs per API, kostelos

BiFunction<Double, Double, String> wechselkursRechner = (betrag, wechselkurs) -> {
double umgerechneterBetrag = betrag * wechselkurs;
return String.format("%.2f", umgerechneterBetrag);
};

double betragInEuro = 100.0;
double wechselkursZuUSD = 1.1; 
String betragInUSD = wechselkursRechner.apply(betragInEuro, wechselkursZuUSD);
System.out.println(betragInEuro + " Euro entspricht " + betragInUSD + " USD");

