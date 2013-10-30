package lab.web.action;

import javax.servlet.http.HttpServletRequest;


public class LabcaseUtil {

    public static Long dealWithLongParameter(HttpServletRequest request, String paramName) {
        return (request.getParameter(paramName) == null ||
                request.getParameter(paramName).isEmpty()) ? null :
                new Long(request.getParameter(paramName));
    }

}
