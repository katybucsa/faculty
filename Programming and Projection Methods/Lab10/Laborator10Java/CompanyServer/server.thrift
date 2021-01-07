namespace java CompanyServer
namespace csharp CompanyClient

typedef i32 int
struct User{
    1:required string username;
    2:required string password;
}

struct Ride{
    1:required int ride_id;
    2:required string destination;
    3:required string date;
    4:required string hour;
    5:optional string places;
}

struct Clientj{
    1:required int client_id;
    2:required string name;
}

struct Booking{
    1:required Clientj client;
    2:required Ride ride;
    3:required int nr_places_wanted;
    4:required string places;
}


struct RBooking{
    1:required string name;
    2:required int place;
}

exception AppException{
    1:required string msg
}

service IObserver{
    oneway void update();
}

service IServer{
    int login(1:User u) throws (1:AppException ex),
    string bookPlaces(1:Ride ride,2:Clientj c,3:int nrplaces) throws (1:AppException ex),
    list<RBooking> findAllRBookings(1:string dest,2:string date,3:string hour),
    void logout(1:User u) throws (1:AppException ex),
    list<Ride> findAllRides() throws (1:AppException ex);
}