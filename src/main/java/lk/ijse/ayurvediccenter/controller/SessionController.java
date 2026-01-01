package lk.ijse.ayurvediccenter.controller;

import lk.ijse.ayurvediccenter.model.enums.UserRole;

public class SessionController {

        private static int currentUserId;
        private static UserRole currentUserRole;

        public static void setUser(int id, UserRole role) {
            currentUserId = id;
            currentUserRole = role;
        }

        public static int getUserId() {
            return currentUserId;
        }

        public static UserRole getUserRole() {
            return currentUserRole;
        }

}
