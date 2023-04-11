package GUI.Controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TicketViewControllerMockTest {
    private static TicketViewControllerMock ticketViewController;

    @BeforeAll
    static void preTest() {
        ticketViewController = new TicketViewControllerMock();
    }

    @Test
    void cbString1() {

        // Arrange - setup our test objects etc.
        ticketViewController.setCbFood(true);
        ticketViewController.setCbVIP(true);

        // Act - do the actual calc or method run
        String actualResult = ticketViewController.cbString();
        String expectedResult = "This ticket includes: vip and free food.";

        // Assert - check if actual value is equal to expected value
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void cbString2() {

        // Arrange - setup our test objects etc.
        ticketViewController.setCbFood(true);
        ticketViewController.setCbVIP(true);
        ticketViewController.setCbFrontRow(true);

        // Act - do the actual calc or method run
        String actualResult = ticketViewController.cbString();
        String expectedResult = "This ticket includes: vip, free food and front row seats.";

        // Assert - check if actual value is equal to expected value
        Assertions.assertEquals(expectedResult, actualResult);
    }
}