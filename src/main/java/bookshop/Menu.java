package bookshop;

class Menu {
    private static final String MENU_TEXT =
            "Aby zmienić sposób wyświetlania wpisz:\n" +
                    "97. rok, tytuł, ISBN\n" +
                    "98. tytuł, rok, ISBN\n" +
                    "99. ISBN, tytuł, tok\n" +
                    "Wybierz opcję: \n" +
                    "1. Wyjście \n" +
                    "2. Kontakt \n" +
                    "3. Pokaż listę książek\n" +
                    "4. Pokaż listę autorów\n" +
                    "5. Pokaż listę kategorii\n" +
                    "6. Znajdź książkę po numerze ISBN\n" +
                    "7. Znajdź najstarszą książkę\n" +
                    "8. Wyświetl książki z kategorii \"Java\"\n" +
                    "9. Wyświetl książki z kategorii \"Wzorce projektowe\" \n" +
                    "10. Wyświetl książki z kategorii \"Techniki programowania\"\n" +
                    "11. Dodaj nowego autora \n" +
                    "12. Dodaj nową kategorię \n" +
                    "13. Edytuj tytuł książki\n" +
                    "14. Edytuj wiek autora\n" +
                    "15. Edytuj nazwę kategorii \n" +
                    "16. Eksportuj listę autorów do pliku csv\n" +
                    "17. Eksportuj listę kategorii do pliku csv \n" +
                    "18. Eksportuj listę książek do pliku csv \n" +
                    "19. Wyświetl listę książek wybranego autora\n" +
                    "20. Wyświetl autorów wg. liczby napisanych książek\n" +
                    "21. Eksportuj wszystko\n" +
                    "22. Usuń książkę\n" +
                    "23. Usuń autora\n" +
                    "24. Usuń kategorię\n" +
                    "25. Zamów dodruk książki";

    static void printMenu() {
        System.out.println(MENU_TEXT);
    }

}

