package ie.gmit.sw;

/**
 * 
 * @author Keith Nolan
 * The language class contains enums of 255 languages.
 * The compared languages are taken from here.
 * It also holds a few necessary variables and methods needed to operate this program
 */
public enum Language {
	Achinese("Achinese"), Afrikaans("Afrikaans"), Albanian("Albanian"), AlemannicGerman("Alemannic German"),
	Amharic("Amharic"), Arabic("Arabic"), Aragonese("Aragonese"), Armenian("Armenian"), Aromanian("Aromanian"),
	Arpitan("Arpitan"), Assamese("Assamese"), Asturian("Asturian"), Avar("Avar"), Aymara("Aymara"),
	Azerbaijani("Azerbaijani"), Banjar("Banjar"), Banyumasan("Banyumasan"), Bashkir("Bashkir"), Basque("Basque"),
	Bavarian("Bavarian"), Belarusian("Belarusian"), BelarusianTaraschkewiza("Belarusian Taraschkewiza"),
	Bengali("Bengali"), Bhojpuri("Bhojpuri"), Bishnupriya("Bishnupriya"), Bokm("Bokm"), Bosnian("Bosnian"),
	Breton("Breton"), Bulgarian("Bulgarian"), Burmese("Burmese"), Buryat("Buryat"), Cantonese("Cantonese"),
	Catalan("Catalan"), Cebuano("Cebuano"), CentralBikol("Central Bikol"), CentralKhmer("Central Khmer"),
	CentralKurdish("Central Kurdish"), Chavacano("Chavacano"), Chechen("Chechen"), Cherokee("Cherokee"),
	Chuvash("Chuvash"), ClassicalNahuatl("Classical Nahuatl"), Cornish("Cornish"), Corsican("Corsican"),
	CrimeanTatar("Crimean Tatar"), Croatian("Croatian"), Czech("Czech"), Danish("Danish"), Dhivehi("Dhivehi"),
	Dimli("Dimli"), Doteli("Doteli"), Dutch("Dutch"), EasternMari("Eastern Mari"), EgyptianArabic("Egyptian Arabic"),
	Emilian("Emilian"), English("English"), Erzya("Erzya"), Esperanto("Esperanto"), Estonian("Estonian"),
	Extremaduran("Extremaduran"), Faroese("Faroese"), FijiHindi("Fiji Hindi"), Finnish("Finnish"), French("French"),
	Friulian("Friulian"), Gagauz("Gagauz"), Galician("Galician"), Georgian("Georgian"), German("German"),
	Gilaki("Gilaki"), Guarani("Guarani"), Gujarati("Gujarati"), HaitianCreole("Haitian Creole"),
	HakkaChinese("Hakka Chinese"), Hausa("Hausa"), Hebrew("Hebrew"), Hindi("Hindi"), Hungarian("Hungarian"),
	Icelandic("Icelandic"), Ido("Ido"), Igbo("Igbo"), Iloko("Iloko"), Indonesian("Indonesian"),
	Interlingua("Interlingua"), Interlingue("Interlingue"), Irish("Irish"), Italian("Italian"),
	JamaicanPatois("Jamaican Patois"), Japanese("Japanese"), Javanese("Javanese"), Kabardian("Kabardian"),
	Kabyle("Kabyle"), Kannada("Kannada"), KarachayBalkar("Karachay Balkar"), Karakalpak("Karakalpak"),
	Kashubian("Kashubian"), Kazakh("Kazakh"), Kinyarwanda("Kinyarwanda"), Kirghiz("Kirghiz"), Komi("Komi"),
	KomiPermyak("Komi Permyak"), Konkani("Konkani"), Korean("Korean"), Kurdish("Kurdish"), Ladino("Ladino"), Lao("Lao"),
	Latgalian("Latgalian"), Latin("Latin"), Latvian("Latvian"), Lezghian("Lezghian"), Ligurian("Ligurian"),
	Limburgan("Limburgan"), Lingala("Lingala"), LiteraryChinese("Literary Chinese"), Lithuanian("Lithuanian"),
	LivviKarelian("Livvi Karelian"), Lojban("Lojban"), Lombard("Lombard"), LowGerman("Low German"),
	LowerSorbian("Lower Sorbian"), Luganda("Luganda"), Luxembourgish("Luxembourgish"), Macedonian("Macedonian"),
	Maithili("Maithili"), Malagasy("Malagasy"), Malay("Malay"), Malayalam("Malayalam"), Maltese("Maltese"),
	Manx("Manx"), Maori("Maori"), Marathi("Marathi"), Mazanderani("Mazanderani"), MinDong("Min Dong"),
	MinNanChinese("MinNan Chinese"), Minangkabau("Minangkabau"), Mingrelian("Mingrelian"), Mirandese("Mirandese"),
	ModernGreek("Modern Greek"), Moksha("Moksha"), Mongolian("Mongolian"), Narom("Narom"), Navajo("Navajo"),
	Neapolitan("Neapolitan"), Nepali("Nepali"), Newari("Newari"), NorthernLuri("Northern Luri"),
	NorthernSami("Northern Sami"), NorthernSotho("Northern Sotho"), NorwegianNynorsk("Norwegian Nynorsk"),
	Occitan("Occitan"), OldEnglish("OldEnglish "), Oriya("Oriya"), Oromo("Oromo"), Ossetian("Ossetian"),
	PalatineGerman("Palatine German"), Pampanga("Pampanga"), Pangasinan("Pangasinan"), Panjabi("Panjabi"),
	Papiamento("Papiamento"), PennsylvaniaGerman("Pennsylvania German"), Persian("Persian"), Picard("Picard"),
	Polish("Polish"), Portuguese("Portuguese"), Pushto("Pushto"), Quechua("Quechua"), Ripuarisch("Ripuarisch"),
	Romanian("Romanian"), Romansh("Romansh"), Russian("Russian"), Rusyn("Rusyn"), Samogitian("Samogitian"),
	Sanskrit("Sanskrit"), Sardinian("Sardinian"), Saterfriesisch("Saterfriesisch"), Scots("Scots"),
	ScottishGaelic("Scottish Gaelic"), Serbian("Serbian"), SerboCroatian("Serbo Croatian"), Shona("Shona"),
	Sicilian("Sicilian"), Silesian("Silesian"), Sindhi("Sindhi"), Sinhala("Sinhala"), Slovak("Slovak"),
	Slovene("Slovene"), Somali("Somali"), SouthAzerbaijani("South Azerbaijani"), Spanish("Spanish"), Sranan("Sranan"),
	StandardChinese("Standard Chinese"), Sundanese("Sundanese"), Swahili("Swahili"), Swedish("Swedish"),
	Tagalog("Tagalog"), Tajik("Tajik"), Tamil("Tamil"), Tarantinodialect("Tarantino Dialect"), Tatar("Tatar"),
	Telugu("Telugu"), Tetum("Tetum"), Thai("Thai"), Tibetan("Tibetan"), Tongan("Tongan"), Tswana("Tswana"),
	Tulu("Tulu"), Turkish("Turkish"), Turkmen("Turkmen"), Tuvan("Tuvan"), Udmurt("Udmurt"), Uighur("Uighur"),
	Ukrainian("Ukrainian"), UpperSorbian("Upper Sorbian"), Urdu("Urdu"), Uzbek("Uzbek"), Venetian("Venetian"),
	Veps("Veps"), Vietnamese("Vietnamese"), Vlaams("Vlaams"), Volapk("Volapk"), Vulro("Vulro"), Walloon("Walloon"),
	Waray("Waray"), Welsh("Welsh"), WestLowGerman("West Low German"), WesternFrisian("Western Frisian"),
	WesternMari("Western Mari"), WesternPanjabi("Western Panjabi"), Wolof("Wolof"), WuChinese("Wu Chinese"),
	Xhosa("Xhosa"), Yakut("Yakut"), Yiddish("Yiddish"), Yoruba("Yoruba"), Zeeuws("Zeeuws");
	
	//final CharSequence language
	private final CharSequence language;
	//constructor
	private Language(CharSequence language) {
		this.language = language;
	}
	//Get the language name return the language
	public CharSequence getLanguageName() {
		return this.language;
	}
	//returns true if the String contains the language
	public boolean contains(String s) {
	
		return false;
	}

}//Language
