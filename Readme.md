# Create a custom JsonSerializer und JsonDeserializer for Jackson to mapping values

**A part of article Series: Mapping FieldValueIDs for the payload of the Emarsys API**

https://dev.to/elevado/create-a-custom-jackson-jsonserializer-und-jsondeserializer-for-mapping-values-48h7

## Example for Annotation @MappingTable with HashMap

A HashMap cannot be defined directly in an annotation.

The following example is <u>**NOT**</u> possible:

```Java
public static Map<Object, Object> salutationMap = new HashMap<>() {{
    put("1", "MALE");
    put("2", "FEMALE");
    put("3", "DIVERS");
}};

@MappingTable(map = salutationMap)
private MappingValue<String> salutation;
```

A HashMap can be used with Enum, Class or nested Annotation:

* [Example with Enum]( https://github.com/alaugks/article-jackson-serializer/tree/mapping-table/enum)
* [Example with Class](https://github.com/alaugks/article-jackson-serializer/tree/mapping-table/class)
* [Example with nested Annotation](https://github.com/alaugks/article-jackson-serializer/tree/mapping-table/annotation)
