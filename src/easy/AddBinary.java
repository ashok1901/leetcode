package easy;

public class AddBinary {
    public String addBinary(String a, String b) {
        if (a == null || a.length() == 0) {
            return b;
        }

        if (b == null || b.length() == 0) {
            return a;
        }
        int indexA = a.length() - 1;
        int indexB = b.length() - 1;
        String result = "";
        char carry = '0';
            while (indexA >= 0 || indexB >= 0) {
            char bitA = indexA >= 0 ? a.charAt(indexA) : '0';
            char bitB = indexB >= 0 ? b.charAt(indexB) : '0';
            indexA--;indexB--;
            if (bitA == '0' && bitB == '0') {
                result = (carry == '0' ? '0' : '1') + result;
                carry = '0';
                continue;
            }

            if (bitA == '1' && bitB == '1') {
                result = (carry == '0' ? '0' : '1') + result;
                carry = '1';
                continue;
            }
            // Below carry is not changing actually, so no need to updat carry and this if-else can be simplified
            // with tertiory operator
            if (carry == '0') {
                result = '1' + result;
                carry = '0';
            } else {
                result = '0' + result;
                carry = '1';
            }
        }

        result = carry == '1' ? '1' + result : result;
        return result;
    }

    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        String a = "1010", b = "1011";
        System.out.println("Binary addition is  " + addBinary.addBinary(a, b));
        String a1 = "11", b1 = "1";
        System.out.println("Binary addition is  " + addBinary.addBinary(a1, b1));
    }
}


