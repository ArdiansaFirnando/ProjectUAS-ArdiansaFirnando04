package com.example.projectuas.fragment;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.projectuas.DetailActivity;
import com.example.projectuas.R;
import com.example.projectuas.adapter.TeamsAdapter;
import com.example.projectuas.model.ModelTeams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Fragment_home extends Fragment {

    private RecyclerView recyclerView;
    private TeamsAdapter teamsAdapter;
    private ArrayList<ModelTeams> teamsArrayList;
    private Toolbar toolbar;

    private String BASE_URL_THESPORTSDB = "https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=4328";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = view.findViewById(R.id.id_toolbar);
        recyclerView = view.findViewById(R.id.id_recyclerview);

        getData();

        return view;
    }

    public void getData() {
        AndroidNetworking.get("https://www.thesportsdb.com/api/v1/json/1/lookup_all_teams.php?id=4328")
                .addPathParameter("pageNumber", "0")
                .addQueryParameter("limit", "3")
                .addHeaders("token", "1234")
                .setTag("test")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            teamsArrayList = new ArrayList<>();

                            JSONArray jsonArray = response.getJSONArray("teams");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String name = object.getString("strTeam");
                                String years = object.getString("intFormedYear");
                                String country = object.getString("strCountry");
                                String description = object.getString("strDescriptionEN");
                                String image = object.getString("strTeamBadge");

                                teamsArrayList.add(new ModelTeams(name, years, country, description, image));
                            }

                            teamsAdapter = new TeamsAdapter(teamsArrayList, new TeamsAdapter.Callback() {
                                @Override
                                public void onClick(int position) {

                                    Intent intent = new Intent(getContext(), DetailActivity.class);
                                    intent.putExtra("image", teamsArrayList.get(position).getImage());
                                    intent.putExtra("name", teamsArrayList.get(position).getName());
                                    intent.putExtra("description", teamsArrayList.get(position).getDescription());
                                    startActivity(intent);
                                }
                            });


                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(layoutManager);
                            recyclerView.setAdapter(teamsAdapter);


                        } catch (JSONException e) {
                            e.printStackTrace();

                            Log.d("error", "onError: " + e.toString());
                        }

                        Log.d("hasil response", "onError: " + response.toString());
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("error", "onError: " + anError.toString());
                    }
                });
    }

    private void dataOffline(){
//        teamsArrayList.add(new ModelTeams("Arsenal", "1892", "English", "Arsenal Football Club is a professional football club based in Islington, London, England, that plays in the Premier League, the top flight of English football. The club has won 13 League titles, a record 13 FA Cups, 2 League Cups, 15 FA Community Shields, 1 League Centenary Trophy, 1 UEFA Cup Winners' Cup and 1 Inter-Cities Fairs Cup. Arsenal was the first club from the South of England to join The Football League, in 1893, and they reached the First Division in 1904. Relegated only once, in 1913, they continue the longest streak in the top division, and have won the second-most top-flight matches in English football history. In the 1930s, Arsenal won five League Championships and two FA Cups, and another FA Cup and two Championships after the war. In 1970–71, they won their first League and FA Cup Double. Between 1989 and 2005, they won five League titles and five FA Cups, including two more Doubles. They completed the 20th century with the highest average league position.Herbert Chapman won Arsenal's first national trophies, but died prematurely. He helped introduce the WM formation, floodlights, and shirt numbers, and added the white sleeves and brighter red to the club's kit. Arsène Wenger was the longest-serving manager and won the most trophies. He won a record 7 FA Cups, and his title-winning team set an English record for the longest top-flight unbeaten league run at 49 games between 2003 and 2004, receiving the nickname The Invincibles.In 1886, Woolwich munitions workers founded the club as Dial Square. In 1913, the club crossed the city to Arsenal Stadium in Highbury, becoming close neighbours of Tottenham Hotspur, and creating the North London derby. In 2006, they moved to the nearby Emirates Stadium. In terms of revenue, Arsenal is the ninth highest-earning football club in the world, earned €487.6m in 2016–17 season. Based on social media activity from 2014 to 2015, Arsenal's fanbase is the fifth largest in the world. In 2018, Forbes estimated the club was the third most valuable in England, with the club being worth $2.24 billion.", R.drawable.logo_arsenal));
//        teamsArrayList.add(new ModelTeams("Aston Villa", "1874", "English", "Aston Villa Football Club (also known as Villa, The Villa, The Villans, The Lions) are a professional football club based in Witton, Birmingham, who play in the Premier League, the highest level of English football. Founded in 1874, they have played at their current home ground, Villa Park, since 1897. Aston Villa were founder members of The Football League in 1888. They were also founder members of the Premier League in 1992, and have remained there ever since. The club were floated by the previous owner and chairman Doug Ellis, but in 2006 full control was acquired by American businessman Randy Lerner. Lerner announced on 12 May 2014 that he is selling the club Aston Villa are among the oldest and the most successful football clubs in the history of English football. Villa won the 1981–82 European Cup, and are thus one of five English clubs to win what is now the UEFA Champions League. They have the fourth highest total of major honours won by an English club, having won the First Division Championship seven times (most recently in the 1980–81 season), the FA Cup seven times (last won in 1957), the Football League Cup five times (last won in 1996) and the UEFA Super Cup in 1982. The club have also produced more England national team players than any other side, currently having produced 72. They have a fierce local rivalry with Birmingham City. The Second City derby between Aston Villa and Birmingham City has been played since 1879. The club's traditional kit colours are claret shirts with sky blue sleeves, white shorts and sky blue socks. Their traditional badge is of a rampant gold lion on a light blue background with the club's motto Prepared underneath, a modified version of this was adopted in 2007.", R.drawable.logo_aston_villa));
//        teamsArrayList.add(new ModelTeams("Brighton", "1901", "English", "Brighton and Hove Albion Football Club is an English football club based in the coastal city of Brighton & Hove, East Sussex. It is often referred to just as Brighton. They currently play in the Football League Championship, the second tier of the English football league system. The team is nicknamed the Seagulls or Albion. The team has historically played in blue and white stripes, though this changed to all white briefly in the 1970s and again to plain blue during the club's most successful spell in the 1980s. Crystal Palace is considered the club's main rival, although the grounds are 40 miles apart.Founded in 1901, Brighton played their early professional football in the Southern League before being elected to the Football League in 1920. The club enjoyed greatest prominence between 1979 and 1983 when they played in the First Division and reached the 1983 FA Cup Final, losing to Manchester United after a replay. They were relegated from the top division in the same season. Mismanagement brought Brighton close to relegation from the Football League to the Conference which they narrowly avoided in 1997 and 1998. A boardroom takeover saved Brighton from liquidation, and following successive promotions they returned to the second tier of English football in 2002 and have played in the second and third tiers ever since.", R.drawable.logo_brighton));
//        teamsArrayList.add(new ModelTeams("Chelsea", "1905", "English", "Crystal Palace Football Club is an English professional football club based in South Norwood, London. They currently play in the highest level in English football, the Premier League. Since 1964, the club have mostly played in the top two leagues of English football. The club was founded in 1905 at the site of the famous Crystal Palace Exhibition building by the owners of the FA Cup Final stadium, who wanted their own team to play at the historic venue. Palace applied to be elected to The Football League, but this was rejected and they instead joined the Southern Football League Second Division, playing home games at The Crystal Palace, inspiration for the club's initial nickname, The Glaziers. Palace won the Division and promotion in their first season, and played in the Southern League First Division for the next fifteen years.In 1920 the Southern League Division One formed the Football League Third Division. Palace won the division and gained promotion to the Second Division, where they spent four seasons before suffering relegation to the Third Division South. In 1958 a league re-organisation saw Palace become founder members of Division Four. Over the next eleven years the club moved from the lowest rung of English Football to the highest, reaching the First Division in 1969, and staying in the top division for four seasons before suffering successive relegations. In 1973 the club modernised its image, changing the nickname from The Glaziers to The Eagles and ending the 68-year association with claret and blue by introducing the red-and-blue vertical stripes now associated with the club. The club stabilised itself in the top two divisions with promotions in 1977 and 1979, the latter saw the club crowned as Division Two Champions. The period between 1989–91 was a successful time for the club. They reached an FA Cup Final in 1990, won the Full Members Cup in 1991, and achieved third place in the top division in the 1990–1991 season. Palace became founder members of the Premier League in 1992, but were relegated the same season, despite achieving 49 points which is still a Premier League record for a relegated club. The club then achieved promotion back to the Premier League three times in 1994, 1997 and 2004, but each time suffered relegation at the end of the following season. Palace entered administration in both 2000 and 2010, and are now owned by a consortium of four. The club were promoted back to the Premier League with a 1–0 win over Watford in the Football League play-offs in May 2013. Crystal Palace initially played their games in the grounds of The Crystal Palace, but the First World War saw them forced to move out, and they enjoyed a number of seasons at both the Herne Hill Velodrome and The Nest. Since 1924, Palace have played their home games at Selhurst Park. Their home colours are red and blue vertical stripes, though prior to 1973 they wore claret and pale blue after the fashion of Aston Villa. They have a fierce rivalry with M23 neighbours Brighton & Hove Albion, with whom they have contested the M23 derby 98 times and also enjoy a strong rivalry with fellow South London team Millwall. The club's kit is currently made by Macron, and the shirt sponsor is Neteller. The club captain is Mile Jedinak and the current player of the year is Julian Speroni. Edmund Goodman is the club's longest serving manager, and Jim Cannon has made the most appearances for the club. Peter Simpson is the club's top scorer for both one season and overall, netting 54 and 165 respectively. The highest transfer fee received has been for Wilfried Zaha from Manchester United in January 2013.", R.drawable.logo_chelsea));
//        teamsArrayList.add(new ModelTeams("Leicester", "1884", "English", "Leicester City Football Club, also known as the Foxes, is an English professional football club based in Leicester at the King Power Stadium. They play in the English Premier League, having been promoted as champions of the Football League Championship in 2013–14. The club was founded in 1884 as Leicester Fosse, playing on a field near Fosse Road. They moved to Filbert Street in 1891 and played there for 111 years, before moving again, this time to the nearby Walkers Stadium in 2002.Leicester City were elected to the Football League in 1894. The club's highest ever finish was second place in the top flight, in Division One in 1928–29. The club holds a joint-highest seven second tier titles (six Second Division and one Championship), as well as one League One title. They have won the League Cup three times and have been FA Cup runners-up four times, a tournament record for the most defeats in the final without having won the competition. The club has only spent one season outside the top two tiers of English football in the 2008/09 season.", R.drawable.logo_leicester));
//        teamsArrayList.add(new ModelTeams("Liverpool", "1892", "English", "Liverpool Football Club is a Premier League football club based in Liverpool. Liverpool F.C. is one of the most successful clubs in England and has won more European trophies than any other English team with five European Cups, three UEFA Cups and three UEFA Super Cups. The club has also won eighteen League titles, seven FA Cups and a record eight League Cups. In spite of their successful history, Liverpool are yet to win a Premier League title since its inception in 1992. Liverpool was founded in 1892 and joined the Football League the following year. The club has played at Anfield since its formation. The most successful period in Liverpool's history was the 1970s and '80s when Bill Shankly and Bob Paisley led the club to eleven league titles and seven European trophies. The club's supporters have been involved in two major tragedies. The first was the Heysel Stadium disaster in 1985, during which charging Liverpool fans caused a crush that resulted in a wall collapsing and the death of 39 Juventus supporters, after which English clubs were banned from European competition for five years. In the 1989 Hillsborough disaster, 96 Liverpool supporters lost their lives in a crush against perimeter fencing. Liverpool has long-standing rivalries with neighbours Everton and with Manchester United. The team changed from red shirts and white shorts to an all-red home strip in 1964. The club's anthem is You'll Never Walk Alone", R.drawable.logo_liverpool));
//        teamsArrayList.add(new ModelTeams("Manchester City", "1880", "English", "Manchester City Football Club is an English football club based in Manchester that competes in the Premier League, the top flight of English football. Founded in 1880 as St. Mark's (West Gorton), it became Ardwick Association Football Club in 1887 and Manchester City in 1894. The club's home ground is the City of Manchester Stadium in east Manchester, to which it moved in 2003, having played at Maine Road since 1923. Manchester City entered the Football League in 1899, and won their first major honour with the FA Cup in 1904. It had its first major period of success in the late 1960s, winning the League, FA Cup and League Cup under the management of Joe Mercer and Malcolm Allison. After losing the 1981 FA Cup Final, the club went through a period of decline, which eventually saw them relegated as far down as third tier of English football by the end of the 1997–98 season. They since regained promotion to the top tier in 2001–02 and have remained a fixture in the Premier League since 2002–03. In 2008, Manchester City was purchased by Abu Dhabi United Group for £210 million and received considerable financial investment. The club have won six domestic league titles. Under the management of Pep Guardiola they won the Premier League in 2018 becoming the only Premier League team to attain 100 points in a single season. In 2019, they won four trophies, completing an unprecedented sweep of all domestic trophies in England and becoming the first English men's team to win the domestic treble. Manchester City's revenue was the fifth highest of a football club in the world in the 2018–19 season at €568.4 million. 2019, Forbes estimated the club was the fifth most valuable in the world at $2.69 billion, however the sale of a 10% stake in the club's parent company City Football Group on 27 November 2019 for $500 million values them significantly higher.", R.drawable.logo_mancherter_city));
//        teamsArrayList.add(new ModelTeams("Manchester United", "1878", "English", "Manchester United Football Club is an English professional football club, based in Old Trafford, Greater Manchester that plays in the Premier League. Founded as Newton Heath LYR Football Club in 1878, the club changed its name to Manchester United in 1902 and moved to Old Trafford in 1910. They are regarded as one of the most successful clubs in English football.Manchester United have won the most League titles (20) of any English club, a joint record 11 FA Cups, four League Cups, and a record 20 FA Community Shields. The club has also won three European Cups, one UEFA Cup Winners' Cup, one UEFA Super Cup, one Intercontinental Cup, and one FIFA Club World Cup. In 1998–99, the club won a continental treble of the Premier League, the FA Cup and the UEFA Champions League. The 1958 Munich air disaster claimed the lives of eight players. In 1968, under the management of Matt Busby, Manchester United was the first English football club to win the European Cup. Alex Ferguson won 28 major honours, and 38 in total, from November 1986 to May 2013, when he announced his retirement after 26 years at the club. On 19 May 2014, Louis van Gaal was appointed as the club's new manager after Ferguson's successor David Moyes was sacked after only 10 months in charge, with the club's record appearance-maker, Ryan Giggs, appointed as his assistant after a brief period as caretaker manager.Manchester United is the third-richest football club in the world for 2011–12 in terms of revenue, with an annual revenue of €395.9 million, and the world's second most valuable sports team in 2013, valued at $3.165 billion. It is one of the most widely supported football teams in the world. After being floated on the London Stock Exchange in 1991, the club was purchased by Malcolm Glazer in May 2005 in a deal valuing the club at almost £800 million, after which the company was taken private again. In August 2012, Manchester United made an initial public offering on the New York Stock Exchange.", R.drawable.logo_mancherter_united));
//        teamsArrayList.add(new ModelTeams("Shouthampton", "1885", "English","Southampton Football Club Listeni is an English football club, nicknamed The Saints, based in the city of Southampton, Hampshire, who currently compete in the Premier League. The Saints' home ground since 2001 has been St Mary's Stadium, before which they were based at The Dell. The club has been nicknamed The Saints since its inception in 1885 due to its history as a church football team, founded as St Mary's Church of England Young Men's Association (or St Mary's Y.M.A) and has since generally played in red and white shirts. The club has a long-standing rivalry with Portsmouth due to its close proximity and both cities' respective maritime history. Matches between the two sides are known as the South Coast Derby. The club has won the FA Cup once in 1976, and their highest-ever league finish was second in the First Division in 1983–84. Southampton were relegated from the Premier League on 15 May 2005 ending 27 successive seasons of top-division football for the club. The club eventually returned to the Premier League after a 7-year absence and have been playing there since the 2012–13 season.", R.drawable.logo_shouthampton));
//        teamsArrayList.add(new ModelTeams("West Ham", "1895", "English","West Ham United Football Club is an English professional football club based in Upton Park, East London, England currently playing in the Premier League, England's top tier of football. The club was founded in 1895 as Thames Ironworks and reformed in 1900 as West Ham United. In 1904 the club relocated to their current Boleyn Ground stadium. They initially competed in the Southern League and Western League before eventually joining the full Football League in 1919 and subsequently enjoyed promotion to the top flight for the 1923 season. 1923 also saw the club feature in the first FA Cup Final to be held at Wembley against Bolton Wanderers. In 1940 the team won the inaugural Football League War Cup. The club has won the FA Cup three times: in 1964, 1975 and 1980 as well as being runners-up twice, in 1923 and 2006. In 1965, they won the European Cup Winners Cup, and in 1999 they won the Intertoto Cup. They are one of eight existent clubs never to have fallen below the second tier of English football, spending 55 of 87 league seasons in Division 1 to 2013. However, unlike the other seven (Arsenal, Chelsea, Everton, Liverpool, Manchester United, Newcastle United and Tottenham Hotspur), the club has never won the league title. The club's best final league position is third place in the 1985–86 First Division. Three West Ham players played significant roles in England's victory in the 1966 World Cup final; captain Bobby Moore, and both goalscorers, Geoff Hurst and Martin Peters.", R.drawable.logo_west_ham))
    }
}

