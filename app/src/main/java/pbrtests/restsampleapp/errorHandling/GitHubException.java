package pbrtests.restsampleapp.errorHandling;

import java.io.IOException;


/*GitHubException is not necessary yet, just one step into direction of later implenting DaggerInjection and RxJava*/
class GitHubException extends IOException {

    private int responseCode;
    private String message;

    public int getResponseCode() {
        return responseCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public GitHubException(int code, String message) {

        this.responseCode = code;
        this.message = message;
    }
}
