package GUI.Controller;

import java.util.ArrayList;
import java.util.List;

public class TicketViewControllerMock {
    boolean cbVIP;
    boolean cbFood;
    boolean cbFreeBeer;
    boolean cbFrontRow;

    public void setCbVIP(boolean cbVIP) {
        this.cbVIP = cbVIP;
    }

    public void setCbFood(boolean cbFood) {
        this.cbFood = cbFood;
    }

    public void setCbFreeBeer(boolean cbFreeBeer) {
        this.cbFreeBeer = cbFreeBeer;
    }

    public void setCbFrontRow(boolean cbFrontRow) {
        this.cbFrontRow = cbFrontRow;
    }

    public String cbString() {
        StringBuilder sb = new StringBuilder();
        List<String> items = new ArrayList<>();
        String s = "This ticket includes: ";

        if (cbVIP) {
            items.add("vip");
        }
        if (cbFood) {
            items.add("free food");
        }
        if (cbFreeBeer) {
            items.add("free beer");
        }
        if (cbFrontRow) {
            items.add("front row seats");
        }

        sb.append(s);

        for (int i = 0; i < items.size(); i++) {
            sb.append(items.get(i));
            if (i == items.size() - 2) {
                sb.append(" and ");
            } else if (i < items.size() - 1) {
                sb.append(", ");
            }

            if (i == items.size() - 1) {
                sb.append(".");
            }
        }

        s = sb.toString();

        if (s.equals("This ticket includes: ")) {
            s = "";
        }
        return s;
    }
}
