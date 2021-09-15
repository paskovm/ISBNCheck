public class Main {

// Expected test results:
//    isbn13("9780316066525") ➞ "Valid"
//    isbn13("0330301824") ➞ "Invalid"
//    isbn13("0316066524") ➞ "9780316066525"
//assertEquals("9788174504944", Challenge.isbn13("817450494X"));

    public static void main(String[] args) {
        System.out.println(isbn13("9780316066525"));
        System.out.println(isbn13("0330301824"));
        System.out.println(isbn13("0316066524"));
        System.out.println(isbn13("9783876155237"));
        System.out.println(isbn13("817450494X"));
    }

    private static String isbn13(String str) {
        String result = "Invalid";

        if (str.length() == 13) {
            result = check13(str);
        } else {
            result = check10(str);
        }

        return result;
    }

    private static String check13(String isbnNumber) {
        int index = 1;
        int sum = 0;
        for (int i = 0; i < isbnNumber.length(); i++) {
            int num = Character.getNumericValue(isbnNumber.charAt(i));
            sum += (index * num);

            index = (index == 1 ? 3 : 1);
        }

        return sum % 10 == 0 ? "Valid" : "Invalid";
    }

    private static String check10(String isbnNumber) {
        int index = 10;
        int sum = 0;
        for (int i = 0; i < isbnNumber.length(); i++) {

            int num;
            if (isbnNumber.charAt(i) == 'X') {
                num = 10;
            } else {
                num = Character.getNumericValue(isbnNumber.charAt(i));
            }

            sum += (index * num);
            index--;
        }

        if (sum % 11 != 0) {
            return "Invalid";
        }

        isbnNumber = "978" + isbnNumber;
        if (check13(isbnNumber) == "Valid") {
            return isbnNumber;
        } else {
            for (int i = 0; i < 10; i++) {
                isbnNumber = isbnNumber.substring(0, isbnNumber.length() - 1) + i;

                if (check13(isbnNumber) == "Valid") {
                    return isbnNumber;
                }

            }

            return "Invalid";
        }
    }
}
