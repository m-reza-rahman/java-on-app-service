const ToDoErrorHanlder = {
    getErrorMessage: (error, defaultMsg) => {
        if (error.response && error.response.data) {
            if (error.response.data.errors) {
                return error.response.data.errors.join(', ');
            }
            return error.response.data.error || defaultMsg;
        }
        return defaultMsg;
    }
};

export default ToDoErrorHanlder;