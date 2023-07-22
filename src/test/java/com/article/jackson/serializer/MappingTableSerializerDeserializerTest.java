package com.article.jackson.serializer;

import com.article.jackson.fixtures.ContactDto;
import com.article.jackson.fixtures.ContactDtoTypeNotSupported;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.javacrumbs.jsonunit.core.Option;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.junit.jupiter.api.Assertions.*;

class MappingTableSerializerDeserializerTest {

    //    public static final HashMap<String, String> payloadAsArray = new HashMap<>() {
    //        {
    //            put("46", "2");
    //            put("1", "Jane");
    //            put("2", "Doe");
    //            put("3", "jane.doe@example.com");
    //            put("4", "1989-11-09");
    //            put("100674", "1");
    //        }
    //    };
    //
    //    public String emarsysPayload;
    //
    //    @BeforeEach
    //    public void setUpBefore() throws JsonProcessingException {
    //        this.emarsysPayload = new ObjectMapper().writeValueAsString(payloadAsArray);
    //    }

    String emarsysPayload = """
            {
                "1": "Jane",
                "2": "Doe",
                "3": "jane.doe@example.com",
                "4": "1989-11-09",
                "46": "2",
                "100674": "1"
            }
            """;

    @Test
    void serialize() throws IOException {
        ContactDto contact = new ContactDto();
        contact.setSalutation("FEMALE");
        contact.setFirstname("Jane");
        contact.setLastname("Doe");
        contact.setEmail("jane.doe@example.com");
        contact.setBirthday(LocalDate.of(1989, 11, 9));
        contact.setMarketingInformation(true);
        String json = new ObjectMapper().writeValueAsString(contact);
        assertThatJson(this.emarsysPayload)
                .when(Option.IGNORING_ARRAY_ORDER)
                .isEqualTo(json);
    }

    @Test
    void deserialize() throws IOException {
        ContactDto contact = new ObjectMapper().readValue(this.emarsysPayload, ContactDto.class);
        assertEquals("FEMALE", contact.getSalutation());
        assertEquals("Jane", contact.getFirstname());
        assertEquals("Doe", contact.getLastname());
        assertEquals("jane.doe@example.com", contact.getEmail());
        assertEquals(LocalDate.of(1989, 11, 9), contact.getBirthday());
        assertTrue(contact.getMarketingInformation());
        assertTrue(contact.isMarketingInformation());
    }

    @Test
    void deserializeNotSupportedType() {
        assertThrows(
            IOException.class,
            () -> new ObjectMapper().readValue(this.emarsysPayload, ContactDtoTypeNotSupported.class)
        );
    }
}
