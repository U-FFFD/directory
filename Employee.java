public class Employee {

    private String _fname;
    private String _lname;
    private String _department;
    private String _phonenum;

    public Employee() {}

    public Employee(String fname, String lname, String department, String phonenum) {
        this._fname = fname;
        this._lname = lname;
        this._department = department;
        this._phonenum = phonenum;
    }

    public String toString() {
        return _lname + ", " + _fname + " " + _phonenum + " " + _department + "\n";
    }

}
