/**
 * Created by I326950 on 4/10/2017.
 */
const components = {
    Home: {
        src: "employee/PersonalCenter",
        meta: {title: '首页'}
    },
    Employee: {
        src: "employee/PersonalCenter",
        meta: {title: '个人中心'}
    },
    Customer: {
        src: "customer/PersonalCenter",
        meta: {title: '个人中心'}
    },
    Supplier: {
        src: "supplier/PersonalCenter",
        meta: {title: '个人中心'}
    },
    Users: {
        src: "employee/UserList",
        meta: {title: '我的'}
    },
    UserBinding: {
        src: "employee/UserBinding",
        meta: {title: '新增绑定'}
    },
    UserDetail: {
        src: "employee/UserDetail",
        meta: {title: '详情'}
    },
    OrderList: {
        src: "order/OrderList",
        meta: {title: '订单'}
    },
    OrderDetail: {
        src: "order/OrderDetail",
        meta: {title: '订单详情'}
    },
    CustomerOrders: {
        src: "order/CustomerOrders",
        meta: {title: '订单'}
    },
    CustomerOrder: {
        src: "order/CustomerOrder",
        meta: {title: '订单详情'}
    },
    CustomerActivities: {
        src: "activity/ActivityList",
        meta: {title: '销售活动'}
    },
    ActivityList: {
        src: "activity/ActivityList",
        meta: {title: '销售活动'}
    },
    ItemList: {
        src: "order/ItemList",
        meta: {title: '产品'}
    },
    PartyList: {
        src: "order/PartyList",
        meta: {title: '相关方'}
    },
    CustomerList: {
        src: "customer/CustomerList",
        meta: {title: '客户'}
    },
    CustomerDetail: {
        src: "customer/CustomerDetail",
        meta: {title: '客户详情'}
    },
    AddressList: {
        src: 'customer/AddressList',
        meta: {title: '地址'}
    },
    CategoryList: {
        src: "product/CategoryList",
        meta: {title: '产品分类'}
    },
    ProductList: {
        src: "product/ProductList",
        meta: {title: '产品'}
    },
    ProductDetail: {
        src: "product/ProductDetail",
        meta: {title: '产品详情'}
    },
    Components: {
        src: "demos/Components",
        meta: {title: '组件'}
    },
    ScrollPage: {
        src: "demos/ScrollPage",
        meta: {title: 'Scroll Page'}
    },
    Toast: {
        src: "demos/Toast",
        meta: {title: 'Toast'}
    },
    Confirm: {
        src: "demos/Confirm",
        meta: {title: 'Confirm'}
    },
    Alert: {
        src: "demos/Alert",
        meta: {title: 'Alert'}
    },
    TabContainer: {
        src: "demos/TabBarContainer/TabBarContainer",
        meta: {title: 'Tab Container'},
        children: [
            {
                path: "page1",
                src: "demos/TabBarContainer/Page1"
            },
            {
                path: "page2",
                src: "demos/TabBarContainer/Page2"
            }
        ]
    },
    FileUpload: {
        src: "demos/FileUpload",
        meta: {title: 'File Upload'}
    },
    ScrollCell: {
        src: "demos/ScrollCell",
        meta: {title: 'Scroll Cell'}
    }
};

export default components;
// import Home from 'home/Home';
// import PersonalCenter from "employee/PersonalCenter";
// import UserList from "employee/UserList";
// import UserBinding from "employee/UserBinding";
// import UserDetail from "employee/UserDetail";
// import Components from "demos/Components";
// import Toast from "demos/Toast";
// import ScrollPage from "demos/ScrollPage";
// import Confirm from "demos/Confirm";
// import Alert from "demos/Alert";
// import TabBarContainer from "demos/TabBarContainer/TabBarContainer";
// import Page1 from "demos/TabBarContainer/Page1";
// import Page2 from "demos/TabBarContainer/Page2";
// import FileUpload from "demos/FileUpload";
// import ScrollCell from "demos/ScrollCell";

// const components = {
//     Home: {
//         src: "employee/PersonalCenter",
//         meta: {title: '首页'}
//     },
//     Me: {
//         src: PersonalCenter,
//         meta: {title: '个人中心'}
//     },
//     Users: {
//         src: UserList,
//         meta: {title: '我的'}
//     },
//     UserBinding: {
//         src: UserBinding,
//         meta: {title: '新增绑定'}
//     },
//     UserDetail: {
//         src: UserDetail,
//         meta: {title: '详情'}
//     },
//     Components: {
//         src: Components,
//         meta: {title: '组件'}
//     },
//     ScrollPage: {
//         src: ScrollPage,
//         meta: {title: 'Scroll Page'}
//     },
//     Toast: {
//         src: Toast,
//         meta: {title: 'Toast'}
//     },
//     Confirm: {
//         src: Confirm,
//         meta: {title: 'Confirm'}
//     },
//     Alert: {
//         src: Alert,
//         meta: {title: 'Alert'}
//     },
//     TabContainer: {
//         src: TabBarContainer,
//         meta: {title: 'Tab Container'},
//         children: [
//             {
//                 path: "page1",
//                 src: Page1
//             },
//             {
//                 path: "page2",
//                 src: Page2
//             }
//         ]
//     },
//     FileUpload: {
//         src: FileUpload,
//         meta: {title: 'File Upload'}
//     },
//     ScrollCell: {
//         src: ScrollCell,
//         meta: {title: 'Scroll Cell'}
//     }
// };

// export default components;