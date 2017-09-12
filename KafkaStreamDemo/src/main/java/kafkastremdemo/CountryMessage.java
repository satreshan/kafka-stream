package kafkastremdemo;

public class CountryMessage {
    /* the JSON messages produced to the countries Topic have this structure:
     { "name" : "The Netherlands"
     , "code" : "NL
     , "continent" : "Europe"
     , "population" : 17281811
     , "size" : 42001
     };

    this class needs to have at least the corresponding fields to deserialize the JSON messages into
    */

    public String code;
    public String name;
    public int population;
    public int size;
    public String continent;
}
