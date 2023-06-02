package com.example.triparrangersfyp.util;

public class Endpoint {

    public static final String BASE_URL = "http://192.168.8.201/TripArrangersAPI/";
    public static final String IMAGE_URL = "http://192.168.8.201/TripArrangersAPI/api";
    public static final String JAZZ_URL = "http://192.168.8.201/hpc/jazz.php?amount=";
    public static final String MAP_URL = "https://maps.googleapis.com/maps/api/";
    public static final String NEARBY_PLACES = MAP_URL + "place/nearbysearch/json?";
    public static final String INSERT_USER = BASE_URL + "api/SignupApi.php";
    public static final String USER_LOGIN = BASE_URL + "api/LoginAPI.php";
    public static final String TA_LOGIN = BASE_URL + "api/TALoginAPI.php";
    public static final String ADMIN_LOGIN = BASE_URL + "api/AdminLoginAPI.php";
    public static final String TRAVEL_AGENECY_SIGNUP = BASE_URL + "api/TravelAgencySignupAPI.php";
    public static final String ADD_TRIP = BASE_URL + "api/AddTripsAPI.php";
    public static final String ADD_CUSTOM_TRIP = BASE_URL + "api/AddCustomTripAPI.php";
    public static final String GET_TRIPS_AGAINST_ID = BASE_URL + "api/GetTripAgainstIDAPI.php";
    public static final String GET_CUSTOMER = BASE_URL + "api/GetAllCustomerAPI.php";
    public static final String GET_TRAVELAGENCY = BASE_URL + "api/GetAllTravelAgencyAPI.php";
    public static final String GET_ALL_TRIPS = BASE_URL + "api/GetAllTripsAPI.php";
    public static final String UPDATE_TRIP_STATUS = BASE_URL + "api/UpdateTripAPI.php";
    public static final String GET_APPROVED_TRIPS = BASE_URL + "api/GetAllApprovedTripsAPI.php";
    public static final String GET_TA_DETAILS_AGAINST_ID = BASE_URL + "api/GetTADetailsAgainstIDAPI.php";
    public static final String GET_SEATS = BASE_URL + "api/GetSeatsAPI.php";
    public static final String Insert_Booking = BASE_URL + "api/InsertBookingAPI.php";
    public static final String GET_BOOKING_TA = BASE_URL + "api/GetTravelAganecyBookingAPI.php";
    public static final String UPDATE_USER_STATUS = BASE_URL + "api/UpdateUserStatusAPI.php";
    public static final String UPDATE_TA_STATUS = BASE_URL + "api/UpdateTAStatusAPI.php";
    public static final String UPDATE_BOOKING_STATUS = BASE_URL + "api/UpdateBookingStatusAPI.php";
    public static final String GET_BOOKING_TOURISTS = BASE_URL + "api/GetouristsBookingAPI.php";
    public static final String UPDATE_TRIP_RECORD = BASE_URL + "api/UpdateTripsDataAPI.php";
    public static final String DELETE_TRIP = BASE_URL + "api/DeleteTripAPI.php";
    public static final String CANCEL_TRIP = BASE_URL + "api/CancelTripAPI.php";
    public static final String Check_Booked_Seats = BASE_URL + "api/CheckSeatsAPI.php";
    public static final String Insert_Fvt_Trip = BASE_URL + "api/InsertFavouriteTripAPI.php";
    public static final String Insert_Feedback = BASE_URL + "api/AddFeedbackAPI.php";
    public static final String GET_FEEDBACK = BASE_URL + "api/GetFeedbackAPI.php";
    public static final String UPDATE_CUSTOMER_PROFILE = BASE_URL + "api/UpdateTouristProfileAPI.php";
    public static final String Insert_Complain = BASE_URL + "api/InsertComplainAPI.php";
    public static final String UPDATE_TRAVELAGENCY_PROFILE = BASE_URL + "api/UpdateProfleTravelAgencyAPI.php";
    public static final String GET_ALL_COMPLAINTS = BASE_URL + "api/GetAllComplaintsAdminAPI.php";
    public static final String GET_COMPLAINTS_TOURIST = BASE_URL + "api/GetComplaintTouristAPI.php";
    public static final String GET_COMPLAINTS_TRAVELAGENCY = BASE_URL + "api/GetComplaintTravelAgencyAPI.php";
    public static final String GET_CUSTOM_TRIPS_AGAINST_ID = BASE_URL + "api/GetCustomTripsAgainstIDAPI.php";
    public static final String GET_LOCATION = BASE_URL + "api/InsertUserLocationAPI.php";
    public static final String Get_Messages = BASE_URL + "api/GetChatAPI.php";
    public static final String Insert_Message = BASE_URL + "api/SendChatMessageAPI.php";
    public static final String Send_Image = BASE_URL + "api/SendChatImageAPI.php";
    public static final String DELETE_CHAT = BASE_URL + "api/DeleteChatAPI.php";
    public static final String GET_TOUR_MEMBERS_API = BASE_URL + "api/GetTourMemberAPI.php";
    public static final String DELETE_MEMBERS_API = BASE_URL + "api/DeleteMemberAPI.php";
    public static final String GET_FAVOURITE_TRIPS = BASE_URL + "api/GetFavouriteTripsAPI.php";
    public static final String UPDATE_CUSTOM_TRIP_RECORD = BASE_URL + "api/UpdateCustomizedTripDataAPI.php";
    public static final String DELETE_CUSTOM_TRIP = BASE_URL + "api/DeleteCustomizedTripAPI.php";
    public static final String GET_APPROVED_USERS = BASE_URL + "api/GetAllApprovedUsersAPI.php";
    public static final String Insert_Member_Group = BASE_URL + "api/AddInGroupAPI.php";
    public static final String Insert_Expense = BASE_URL + "api/InsertExpenseAPI.php";
    public static final String GET_CUSTOM_TRIP_MEMBERS = BASE_URL + "api/GetCustomTripMembersAPI.php";
    public static final String Get_Customers_Custom = BASE_URL + "api/CustomerAddedinCTripAPI.php";
    public static final String UPDATE_CUSTOM_TRIP_STATUS = BASE_URL + "api/UpdateCustomTripStatusAPI.php";
    public static final String GET_EXPENSE = BASE_URL + "api/GetExpenseAPI.php";
    public static final String UPDATE_CUSTOM_TRIP_STATUS_COMPLETE = BASE_URL + "api/UpdateCompletedCustomTripStatusAPI.php";
    public static final String Insert_Notification = BASE_URL + "api/InsertNotificationAPI.php";
    public static final String GET_NOTIFICATION_AGAINST_ID = BASE_URL + "api/GetNotificationAPI.php";
    public static final String GET_EXPENSE_OF_MEMBER = BASE_URL + "api/GetExpenseOfMemberAPI.php";
    public static final String UPDATE_ADMIN_PROFILE = BASE_URL + "api/UpdateAdminProfileAPI.php";
    public static final String GET_ALL_REGISTERED_USER = BASE_URL + "api/ReportRegisteredUserAPI.php";
    public static final String REPORT_FEEDBACK = BASE_URL + "api/ReportFeedbackAPI.php";
    public static final String GET_ALL_REGISTERED_TRIPS = BASE_URL + "api/ReportRegisteredTripsAPI.php";
    public static final String UPDATE_TRAVELAGENCY_PASSWORD = BASE_URL + "api/ChangePasswordTravelAPI.php";
    public static final String UPDATE_CUSTOMER_PASSWORD = BASE_URL + "api/ChangePasswordTouristAPI.php";
    public static final String REPORT_BOOKING = BASE_URL + "api/ReportViewBookingAPI.php";
    public static final String GET_TRAVEL_AGENCY_WISE_REPORT = BASE_URL + "api/ReportTravelAgencyWiseAPI.php";
    public static final String DATEWISE_REPORT = BASE_URL + "api/ReportDatewiseAPI.php";




}
