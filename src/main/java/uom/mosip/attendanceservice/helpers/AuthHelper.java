package uom.mosip.attendanceservice.helpers;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import uom.mosip.attendanceservice.dto.auth.UserDetails;

@Service
public class AuthHelper {

    public UserDetails getCurrentUserDetails() {
        Object authenticatedUserDetails = RequestContextHolder.currentRequestAttributes().getAttribute("authenticatedUserDetails",
                RequestAttributes.SCOPE_REQUEST);

        if (authenticatedUserDetails == null) {
            throw new RuntimeException("There is no user details for this request. " +
                    "The request probably does not require authentication.");
        }

        return (UserDetails) authenticatedUserDetails;
    }
}
