import { apiInstance } from "./index.js";

const api = apiInstance();

function registerUser(user, success, fail) {
  api.post(`/user/register`, JSON.stringify(user)).then(success).catch(fail);
}

function duplicateId(userId, success, fail) {
  api.get(`/user/check/${userId}`).then(success).catch(fail);
}
export { registerUser };
