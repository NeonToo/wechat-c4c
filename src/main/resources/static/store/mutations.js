/**
 * Created by I326950 on 3/14/2017.
 */
export default {
    userConfiguration (state, oConfiguration) {
        state.userConfiguration = oConfiguration;
    },
    currentIndex (state, newIndex) {
        state.currentIndex = newIndex;
    },
    defaultUser (state, newDefaultUser) {
        state.defaultUser = newDefaultUser;
    },
    toastModal (state, newToast) {
        state.toast = _.cloneDeep(newToast);
    },
    confirmModal (state, newConfirm) {
        state.confirm = _.cloneDeep(newConfirm);
    },
    alertModal (state, newAlert) {
        state.alert = _.cloneDeep(newAlert);
    },
    isLoading(state, bLoading) {
        state.isLoading = bLoading;
    },
    showToast(state) {
        state.toast.show = true;
    },
    hideToast(state) {
        state.toast.show = false;
    },
    showConfirm(state) {
        state.confirm.show = true;
    },
    hideConfirm(state) {
        state.confirm.show = false;
    },
    showAlert(state) {
        state.alert.show = true;
    },
    hideAlert(state) {
        state.alert.show = false;
    }
}