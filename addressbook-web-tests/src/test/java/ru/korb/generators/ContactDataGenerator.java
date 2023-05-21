package ru.korb.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.korb.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Contact count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;

    public static  void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")) {
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("json")){
            saveAsJson(contacts, new File(file));
        } else{
            System.out.println("Unknown format " + format);
        }

    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)) {
            for (ContactData contact:contacts){
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",contact.getFirstname(),contact.getMiddlename(), contact.getLastname(), contact.getNickname(),contact.getPhoto(), contact.getCompany(), contact.getAddress(), contact.getHome(), contact.getMobile()
                        , contact.getWork(), contact.getEmail(), contact.getEmail2(), contact.getEmail3(), contact.getBday(), contact.getBmonth(), contact.getByear(), contact.getAddress2(), contact.getPhone2()));
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i=0; i < count; i++){
            File photo1 = new File("src/test/resources/Bin.png");
            contacts.add(new ContactData().withFirstname("Ivan").withMiddlename("Ivanovich").withLastname("Ivanov").withNickname("Korb").withPhoto(photo1).withCompany("Sber").withAddress("Saint-Petersburg").withHome("345-46-14").withMobile("8-901-363-52-38").withWork("363-99-99").withEmail("korb@yandex.ru").withEmail2("korb@sber.ru").withEmail3("korb@mail.ru").withBday("3").withBmonth("March").withByear("1968").withAddress2("Kudrovo").withPhone2("363-15-43"));
            File photo2 = new File("src/test/resources/Vin.jpg");
            contacts.add(new ContactData().withFirstname("Igor").withMiddlename("Sergeevich").withLastname("Pupkin").withNickname("Plum").withPhoto(photo2).withCompany("GazProm").withAddress("Moscow").withHome("235-62-52").withMobile("8-911-474-36-38").withWork("344-47-42").withEmail("Plum@yandex.ru").withEmail2("Plum@sber.ru").withEmail3("Plum@mail.ru").withBday("7").withBmonth("May").withByear("1993").withAddress2("Shushary").withPhone2("256-52-81"));
            File photo3 = new File("src/test/resources/Santa.jpg");
            contacts.add(new ContactData().withFirstname("Alex").withMiddlename("Anatolevich").withLastname("Shatov").withNickname("Grib").withPhoto(photo3).withCompany("Obit").withAddress("Smolensk").withHome("346-24-46").withMobile("8-921-675-47-85").withWork("679-58-52").withEmail("Grib@yandex.ru").withEmail2("Grib@sber.ru").withEmail3("Grib@mail.ru").withBday("4").withBmonth("July").withByear("1972").withAddress2("Sosnovo").withPhone2("832-68-42"));
            File photo4 = new File("src/test/resources/Brus.jpg");
            contacts.add(new ContactData().withFirstname("Egor").withMiddlename("Vladimirovich").withLastname("Bobov").withNickname("Tron").withPhoto(photo4).withCompany("YourNet").withAddress("Tver").withHome("588-25-16").withMobile("8-905-568-57-47").withWork("346-47-83").withEmail("Tron@yandex.ru").withEmail2("Tron@sber.ru").withEmail3("Tron@mail.ru").withBday("9").withBmonth("November").withByear("1988").withAddress2("Tosno").withPhone2("478-53-27"));
        }


        return contacts;
    }
}
