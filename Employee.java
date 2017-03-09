public class Employee implements Comparable<Employee>{

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

    public int compareTo(Employee other){
      if (this._lname.equals(other._lname)){
        return this._fname.compareTo(other._fname);
      }else{
        return this._lname.comapreTo(other._fname);
      }
    }

}
