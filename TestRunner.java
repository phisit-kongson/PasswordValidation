// ดูโจทย์ วิธีทำใน README.md
// หน้าที่ของคุณ: ออกแบบ test เอง แล้วเติม check(...) ให้ครบทุก branch
public class TestRunner {

    static int pass = 0, fail = 0;

    static void check(String name, boolean ok) {
        if (ok) { pass++; System.out.println("  [PASS] " + name); }
        else    { fail++; System.out.println("  [FAIL] " + name); }
    }

    public static void main(String[] a) {
        boolean ea = false;
        assert ea = true;
        if (!ea) System.out.println("** คำเตือน: assertion ปิดอยู่ รันด้วย  java -ea TestRunner **");

        System.out.println("== Password Validation ==");

        // ตัวอย่าง assertion ปกติ (ตัวแทนกลุ่ม valid)
        check("'Abcdef12' valid", PasswordValidator.validate("Abcdef12"));

        // ตัวอย่างแพตเทิร์นทดสอบ "ต้อง throw" ด้วย try/catch
        boolean threw = false;
        try { PasswordValidator.validate(null); }
        catch (IllegalArgumentException e) { threw = true; }
        check("null -> throws IllegalArgumentException", threw);

        // TODO: R2 - boundary ความยาว (เช่น 7, 8, 20, 21)
        check("password len < 8",PasswordValidator.validate("Aabcse1")==false );
        check("password len = 8",PasswordValidator.validate("Aabcse12")==true );
        check("password len = 20",PasswordValidator.validate("AaaBbb1234567890abcd")==true);
        check("password len > 20",PasswordValidator.validate("AaaBbb1234567890abcde")==false );

        // TODO: R3 - ไม่มีตัวพิมพ์ใหญ่ -> false
        check("password no upper", PasswordValidator.validate("aaabbb12")==false );

        // TODO: R4 - ไม่มีตัวพิมพ์เล็ก -> false
        check("password no lower", PasswordValidator.validate("AAABBB12")==false );

        // TODO: R5 - ไม่มีตัวเลข -> false
        check("password no number", PasswordValidator.validate("AaBbCcDd")==false );

        // TODO: R6 - มีช่องว่าง -> false
        check("password has space", PasswordValidator.validate("Abc 12345")==false );

        // TODO: boundary อื่นๆ ที่คุณคิดว่าจำเป็น
        check("password has special characters",PasswordValidator.validate("Abcd_123#")==true);

        System.out.println("==================================");
        System.out.printf("PASS %d / FAIL %d%n", pass, fail);
        System.out.println("==================================");
        System.exit(fail == 0 ? 0 : 1);
    }
}
