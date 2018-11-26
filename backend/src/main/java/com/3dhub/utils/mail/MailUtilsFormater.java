package com.greencrane.utils.mail;

import com.greencrane.entity.Customer;
import org.springframework.stereotype.Component;

/**
 * Created by Marcin on 24.09.2018.
 */
public interface MailUtilsFormater {
    String formatTitle(Object o);
    String formatContent(Object o);
}

@Component("mailCustomerFormaterUtils")
class MailCustomerFormaterUtils implements MailUtilsFormater {
    @Override
    public String formatTitle(Object o) {
        if (o instanceof Customer) {
            Customer c = (Customer) o;
            return new StringBuilder()
                    .append("Prosba o kontakt od ")
                    .append(c.getName())
                    .append(" !")
                    .toString();
        }
        return "error";
    }
    @Override
    public String formatContent(Object o) {
        if (o instanceof Customer) {
            Customer c = (Customer) o;
            return new StringBuilder()
                    .append("<ul>")
                    .append("<li>")
                    .append("Imie i nazwisko -> ")
                    .append(c.getName())
                    .append("</li>")
                    .append("<li>")
                    .append("Email -> ")
                    .append(c.getEmail())
                    .append("</li>")
                    .append("<li>")
                    .append("Nr telefonu -> ")
                    .append(c.getMobPhone())
                    .append("</li>")
                    .append("<li>")
                    .append("Umiejetnosci -> ")
                    .append(c.getSkills())
                    .append("</li>")
                    .append("<li>")
                    .append("Dodatkowa wiadomosc -> ")
                    .append(c.getAdditionalInfo())
                    .append("</li>")
                    .append("</ul>")
                    .toString();
        }
        return "error";
    }
}

@Component("mailToCustomerFormaterUtils")
class MailToCustomerFormaterUtils implements MailUtilsFormater {
    private static final String GREEN_CRANE_CREW = "<span style=\"font-size:14px; color: " +
            "green\">green-crane</span>";

    @Override
    public String formatTitle(Object o) {
        if (o instanceof Customer) {
            Customer c = (Customer) o;
            return new StringBuilder()
                    .append("Witaj ")
                    .append(c.getName())
                    .append("!")
                    .toString();
        }
        return "error";
    }
    @Override
    public String formatContent(Object o) {
        if (o instanceof Customer) {
            Customer c = (Customer) o;
            return new StringBuilder()
                    .append("<b>")
                    .append("Cze&#347;&#263;!")
                    .append("</b>")
                    .append("<br>")
                    .append("<p>")
                    .append("Wla&#347;nie skontaktowa&#322;e&#347; sie z ekip&#261; ")
                    .append(GREEN_CRANE_CREW)
                    .append(".")
                    .append("<br>")
                    .append(" Potrzebujemy troch&#281; czasu na przetworzenie Twojej aplikacji,")
                    .append(" jednak b&#261;d&#378; pewien")
                    .append(", &#380;e skontaktujemy si&#281; z Tob&#261; tak szybko")
                    .append(" jak to mo&#380;liwe.")
                    .append("<br>")
                    .append("Niech moc b&#281;dzie z Tob&#261; i do zobaczenia!")
                    .append("</p>")
                    .append("<br>")
                    .append("<b>")
                    .append("Pozdrawiamy,")
                    .append("</b>")
                    .append("<br>")
                    .append("Ekipa ")
                    .append(GREEN_CRANE_CREW)
                    .append("!")
                    .toString();
        }
        return "error";
    }
}
