public class Employee implements Comparable<Employee>{

    protected enum Gender{
      MALE("male"),
      FEMALE("female"),
      OTHER("other");

      protected final String str;

      public String toString(){
        return str;
      }

      Gender(String str){this.str = str;}
    }

    protected enum Prefix{
      MR("Mr. "),
      MS("Ms. "),
      MRS("Mrs. "),
      DR("Dr. "),
      COL("Col. "),
      PROF("Prof. ");

      protected final String str;

      public String toString(){
        return str;
      }
      Prefix(String str){this.str = str;}
    }

    protected String _fname;
    protected String _lname;
    protected String _department;
    protected String _phonenum;

    protected String _gender;
    protected Prefix _prefix;

    public Employee() {}

    public Employee(String fname, String lname, String department, String phonenum, String gender, Prefix prefix) {
        this._fname = fname;
        this._lname = lname;
        this._department = department;
        this._phonenum = phonenum;
        this._gender = gender;
        this._prefix = prefix;
    }

    public String toString() {
        return _prefix.toString() + _fname + " " + _lname + "; " + _gender.toString() + " " + _phonenum + " " + _department + "\n";
    }

    @Override public int compareTo(Employee other){
      if (this._lname.equals(other._lname)){
        return this._fname.compareTo(other._fname);
      }else{
        return this._lname.compareTo(other._fname);
      }
    }

}
