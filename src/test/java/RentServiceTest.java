import com.myspringapp.carsrentalstore.model.Rent;
import com.myspringapp.carsrentalstore.service.RentServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RentServiceTest {

    @Mock
    RentServiceImpl rentService;

    @Spy
    Rent rent;

    @Test
    public void getAllRentsTest(){
        rentService.getAllRents();
        verify(rentService).getAllRents();
    }

    @Test
    public void getRentById(){
        rentService.getRentById(1);
        verify(rentService).getRentById(1);
    }

    @Test
    public void addNewRentTest(){
        rentService.saveOrUpdate(rent);
        verify(rentService).saveOrUpdate(rent);
    }

    @Test
    public void deleteRentTest(){
        rentService.deleteRent(rent);
        verify(rentService).deleteRent(rent);
    }
}
