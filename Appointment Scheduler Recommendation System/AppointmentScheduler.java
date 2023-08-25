
package appointment;
import java.time.LocalDateTime;
import java.util.*;
public class AppointmentScheduler 
{
	private List<User> users;
    private List<Appointment> appointments;

    public AppointmentScheduler() {
        users = new ArrayList<>();
        appointments = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean isValidUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public void addAppointment(LocalDateTime startTime, LocalDateTime endTime, String description, User user) {
        Appointment appointment = new Appointment(startTime, endTime, description, user);
        appointments.add(appointment);
    }

    public List<Appointment> getAppointmentsByUser(User user) {
        List<Appointment> userAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getUser().equals(user)) {
                userAppointments.add(appointment);
            }
        }
        return userAppointments;
    }

    public List<Appointment> getAllAppointments() {
        return appointments;
    }

    public List<Appointment> getAppointmentsInDateRange(LocalDateTime startRange, LocalDateTime endRange) {
        List<Appointment> filteredAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            LocalDateTime startTime = appointment.getStartTime();
            if (startTime.isAfter(startRange) && startTime.isBefore(endRange)) {
                filteredAppointments.add(appointment);
            }
        }
        return filteredAppointments;
    }
    
    public List<LocalDateTime> findAvailableSlots(LocalDateTime date, int durationInMinutes) {
        List<LocalDateTime> availableSlots = new ArrayList<>();
        
        LocalDateTime startOfDay = date.withHour(8).withMinute(0); // Start of working hours
        LocalDateTime endOfDay = date.withHour(17).withMinute(0);  // End of working hours
        
        // Retrieve existing appointments for the given date
        List<Appointment> appointmentsOnDate = getAppointmentsInDateRange(startOfDay, endOfDay);
        
        LocalDateTime currentSlot = startOfDay;
        
        while (currentSlot.plusMinutes(durationInMinutes).isBefore(endOfDay)) {
            boolean slotAvailable = true;
            
            for (Appointment appointment : appointmentsOnDate) {
                if (currentSlot.isAfter(appointment.getStartTime()) &&
                    currentSlot.plusMinutes(durationInMinutes).isBefore(appointment.getEndTime())) {
                    slotAvailable = false;
                    break;
                }
            }
            
            if (slotAvailable) {
                availableSlots.add(currentSlot);
            }
            
            currentSlot = currentSlot.plusMinutes(durationInMinutes);
        }
        
        return availableSlots;
    }
    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }
   
  
	}
