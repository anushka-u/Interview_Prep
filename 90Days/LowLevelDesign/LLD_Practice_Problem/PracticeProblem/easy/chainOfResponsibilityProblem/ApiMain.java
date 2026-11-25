package PracticeProblem.easy.chainOfResponsibilityProblem;

interface Middleware {
    void setNext(Middleware next);
    Middleware getNext();
    String check(ApiRequest request);
}

abstract class BaseMiddleware implements Middleware {
    // TODO: Store next middleware
    private Middleware nextMiddleware;
    // TODO: Implement setNext() and getNext()
    @Override
    public void setNext(Middleware next) {
        this.nextMiddleware = next;
    }

    @Override
    public Middleware getNext() {
        return nextMiddleware;
    }
    // TODO: Implement shared forwarding helper
    protected String forward(ApiRequest request) {
        if(nextMiddleware != null) {
            return  nextMiddleware.check(request);
        }
        return  "Request Accepted";
    }
}

class AuthenticationMiddleware extends BaseMiddleware {
    // TODO
    @Override
    public String check(ApiRequest request) {
        if("valid-token".equals(request.getToken())){
            return forward(request);
        }
        return "Authentication Failed";
    }
}

class AuthorizationMiddleware extends BaseMiddleware {
    // TODO
    @Override
    public String check(ApiRequest request) {
        if(request.getRole().equals("ADMIN")){
            return  forward(request);
        }
        return  "Authorization Failed";
    }
}

class RateLimitMiddleware extends BaseMiddleware {
    // TODO
    @Override
    public String check(ApiRequest request) {
        if(request.getRequestCount()>=0 && request.getRequestCount()<=100){
            return   forward(request);
        }
        return   "Rate Limit Exceeded";
    }
}

class ApiRequest {
    String token;
    String role;
    int requestCount;

    public  ApiRequest(String token, String role, int requestCount) {
        this.token = token;
        this.role = role;
        this.requestCount = requestCount;
    }

    public  String getToken() {
        return token;
    }
    public String getRole() {return role;}

    public  int getRequestCount() {return requestCount;}
}

class ApiGateway {
    // TODO: Configure middleware chain
    // TODO: Process API request

    private Middleware nextMiddleware;

    public ApiGateway(){
        Middleware authenticationMiddleware = new AuthenticationMiddleware();
        Middleware authorizationMiddleware = new AuthorizationMiddleware();
        Middleware rateLimitMiddleware = new RateLimitMiddleware();

        authenticationMiddleware.setNext(authorizationMiddleware);
        authorizationMiddleware.setNext(rateLimitMiddleware);

        nextMiddleware = authenticationMiddleware;
    }

    public String processRequest(ApiRequest request){
        return nextMiddleware.check(request);
    }
}

public class ApiMain{
    static void main() {
        ApiRequest request = new ApiRequest("valid-token", "USER", 1);
        ApiGateway executeRequest = new ApiGateway();
        String result = executeRequest.processRequest(request);
        IO.println(result);
    }
}
