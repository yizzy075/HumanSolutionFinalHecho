package co.edu.uco.HumanSolution.controller.dto.response;

public class ResponseDTO<T> {

    private boolean success;
    private String message;
    private T data;

    // Constructor por defecto
    public ResponseDTO() {
    }

    // Constructor con parámetros
    public ResponseDTO(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    // Getters y Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    // Builder estático
    public static <T> ResponseDTOBuilder<T> builder() {
        return new ResponseDTOBuilder<>();
    }

    // Clase Builder interna
    public static class ResponseDTOBuilder<T> {
        private boolean success;
        private String message;
        private T data;

        public ResponseDTOBuilder<T> success(boolean success) {
            this.success = success;
            return this;
        }

        public ResponseDTOBuilder<T> message(String message) {
            this.message = message;
            return this;
        }

        public ResponseDTOBuilder<T> data(T data) {
            this.data = data;
            return this;
        }

        public ResponseDTO<T> build() {
            return new ResponseDTO<>(success, message, data);
        }
    }
}