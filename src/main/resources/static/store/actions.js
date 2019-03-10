/**
 * Created by I326950 on 3/21/2017.
 */
export default {
    resetComponents({state, commit}) {
        commit("toastModal", state.originToast);
        commit("confirmModal", state.originConfirm);
        commit("alertModal", state.originAlert);
    },
    resetToast({state, commit}) {
        commit("toastModal", state.originToast);
    },
    resetConfirm({state, commit}) {
        commit("confirmModal", state.originConfirm);
    },
    resetAlert({state, commit}) {
        commit("alertModal", state.originAlert);
    }
}
