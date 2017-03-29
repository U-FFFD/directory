public class Employee implements Comparable<Employee>{

    protected enum Gender{
      MALE("male"),
      FEMALE("female"),
      OTHER("other");

      private final String str;

      public String toString(){
        return str;
      }

      private Gender(String str){this.str = str;}
    }

    protected enum Title{
      MR("Mr. "),
      MS("Ms. "),
      MRS("Mrs. "),
      DR("Dr. "),
      COL("Col. "),
      PROF("Prof. ");

      private final String str;

      public String toString(){
        return str;
      }

      private Title(String str){this.str = str;}
    }

    private String _fname;
    private String _lname;
    private String _department;
    private String _phonenum;

    private Gender _gender;
    private Title _title;

    public Employee() {}

    public Employee(String fname, String lname, String department, String phonenum, Gender gender, Title title) {
        this._fname = fname;
        this._lname = lname;
        this._department = department;
        this._phonenum = phonenum;
        this._gender = gender;
        this._title = title;
    }

    public String toString() {
        return _title.toString() + _fname + " " + _lname + "; " + _gender.toString() + " " + _phonenum + " " + _department + "\n";
    }

    public String toTable() {
      return "<tr>\n"
      + "<td>" + _title.toString() + "</td>\n"
      + "<td>" + _fname + "</td>\n"
      + "<td>" + _lname + "</td>\n"
      + "<td>" + _gender.toString() + "</td>\n"
      + "<td>" + _phonenum + "</td>\n"
      + "<td>" + _department + "</td>\n"
      + "</tr>";
    }

    @Override public int compareTo(Employee other){
      if (this._lname.equals(other._lname)){
        return this._fname.compareTo(other._fname);
      }else{
        return this._lname.compareTo(other._fname);
      }
    }

}
