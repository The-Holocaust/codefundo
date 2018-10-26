package com.example.anmolbhat.codefundo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class precaution_activity extends AppCompatActivity {

    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.precaution_layout);

        Spinner precaution = findViewById(R.id.spinnerprecaution);
        String[] disasters=getResources().getStringArray(R.array.disaster_array);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter(this, R.layout.custom_spinner, disasters);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        precaution.setAdapter(dataAdapter);
        precaution.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                prepareListData(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("nothing");
            }
        });
    }
    private void prepareListData(String disaster) {
        listDataHeader = new ArrayList();
        listDataChild = new HashMap();
        ExpandableListView explvprec = findViewById(R.id.explvprec);
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        explvprec.setAdapter(listAdapter);
        List<String> before = new ArrayList();
        List<String> during = new ArrayList();
        List<String> after = new ArrayList();
        listDataHeader.add("Before "+disaster);
        listDataHeader.add("During "+disaster);
        listDataHeader.add("After "+disaster);

        switch (disaster) {
            case "Earthquake":

                before.add("Install latches on cupboard doors to prevent them from opening during a quake.");
                before.add("Use non-skid shelf liners for kitchen and bathroom cupboards, medicine cabinets, and closet shelves.");
                before.add("Store heavy items or glassware in lower cabinets so they do not become dangerous projectiles.");
                before.add("Update home insurance policies to adequately cover building costs, possession replacement, and injury deductibles.");
                before.add("Do not put heavy artwork, mirrors, or shelves over beds.");
                before.add("Keep cell phones charged and replace emergency kit supplies as necessary to keep them usable.");
                before.add("Plan alternative commuting routes in case an earthquake damages roads.");
                before.add("Set up a family meeting location in a safe area.");

                during.add("Immediately seek a safe location such as in a doorway (if you live in an old, adobe house that is not reinforced), beneath a table or desk, or along an interior wall away from windows or hazardous objects.");
                during.add("Cover the back of your head and your eyes to minimize injury from flying debris.");
                during.add("Do not take elevators during an earthquake.");
                during.add("If cooking, turn off heating elements immediately.");
                during.add("If outdoors, stay in open areas away from buildings, power lines, trees, and other potential hazards.");
                during.add("If driving, stop quickly but safely and stay in the vehicle. Do not stop near power lines, bridges, overpasses, or other potentially dangerous locations.");
                during.add("Stay calm and brace yourself to keep your balance, sitting if possible.");

                after.add("Be prepared for aftershocks, which may be stronger than the initial jolt.");
                after.add("Tend injuries immediately and summon emergency assistance if necessary.");
                after.add("Check for structural damage, but do not enter a building that shows damage or has visible cracks in the walls or foundation.");
                after.add("Wear shoes at all times to avoid stepping on broken glass.");
                after.add("Turn off gas, electricity, and water if damage is suspected or if advised to do so by authorities.");
                after.add("Keep phone lines clear for emergency use.");
                after.add("Be patient: It may take hours or days to restore all services depending on the severity of the quake.");

                break;
            case "Tsunami":

                before.add("If you live near, or regularly visit a coastal area, learn about the risk of tsunami in the area. Some at-risk communities have maps with evacuation zones and routes. If you are a visitor, ask about community plans.");
                before.add("Learn the signs of a potential tsunami, such as an earthquake, a loud roar from the ocean, or unusual ocean behavior, such as a sudden rise or wall of water or sudden draining of water showing the ocean floor.");
                before.add("Know and practice community evacuation plans and map out your routes from home, work, and play. Pick shelters 100 feet or more above sea level, or at least one mile inland.");
                before.add("Consider earthquake insurance and a flood insurance policy through the National Flood Insurance Program (NFIP). Standard homeowner’s insurance does not cover flood or earthquake damage..");
                before.add("Create a family emergency communication plan that has an out-of-state contact. Plan where to meet if you get separated.");

                during.add("If you are in a tsunami area and there is an earthquake, then first protect yourself from the earthquake. Drop, Cover, and Hold On. Drop to your hands and knees. Cover your head and neck with your arms. Hold on to any sturdy furniture until the shaking stops. Crawl only if you can reach better cover, but do not go through an area with more debris.");
                during.add("When the shaking stops, if there are natural signs or official warnings of a tsunami, then move immediately to a safe place as high and as far inland as possible. Listen to the authorities, but do not wait for tsunami warnings and evacuation orders.");
                during.add("If you are outside of the tsunami hazard zone and receive a warning, then stay where you are unless officials tell you otherwise.");
                during.add("Leave immediately if you are told to do so. Evacuation routes are often marked by a wave with an arrow in the direction of higher ground.");
                during.add("If you are in the water, then grab onto something that floats, such as a raft, tree trunk, or door.");
                during.add("If you are in a boat, then face the direction of the waves and head out to sea. If you are in a harbor, then go inland.");

                after.add("Listen to local alerts and authorities for information on areas to avoid and shelter locations.");
                after.add("Avoid wading in floodwater, which can contain dangerous debris. Water may be deeper than it appears.");
                after.add("Be aware of the risk of electrocution. Underground or downed power lines can electrically charge water. Do not touch electrical equipment if it is wet or if you are standing in water.");
                after.add("Stay away from damaged buildings, roads, and bridges.");
                after.add("Document property damage with photographs. Conduct an inventory and contact your insurance company for assistance.");
                after.add("Save phone calls for emergencies. Phone systems are often down or busy after a disaster. Use text messages or social media to communicate with family and friends.");

                break;
            case "Flood":

                before.add("Elevate the furnace, water heater, and electric panel if susceptible to flooding.");
                before.add("Install check valves in sewer traps to prevent floodwater from backing up into your home.");
                before.add("Seal walls in basements with waterproofing compounds to avoid seepage.");
                before.add("Keep an adequate supply of food, candles and drinking water in case you are trapped inside your home.");
                before.add("Listen to designated radio/TV emergency alert systems for emergency instructions.");
                before.add("Secure/bring in outdoor furniture or other items that might float away and become a potential hazard.");
                before.add("Move valuable items and papers/documents to upper floors.");

                during.add("Seek higher ground. Do not wait for instructions.");
                during.add("Be aware of flash flood areas such as canals, streams, drainage channels.");
                during.add("Be ready to evacuate.");
                during.add("If instructed, turn off utilities at main switches and unplug appliances - do not touch electrical equipment if wet.");
                during.add("If you must leave your home, do not walk through moving water. Six inches of moving water can knock you off your feet. Use a stick to test depth.");
                during.add("Do not try to drive over a flooded road. If your car stalls, abandon it immediately and seek an alternate route.");

                after.add("Stay away from flood water - do not attempt to swim, walk or drive through the area");
                after.add("Be aware of areas where water has receded. Roadways may have weakened and could collapse.");
                after.add("Avoid downed power lines and muddy waters where power lines may have fallen.");
                after.add("Do not drink tap water until advised by the Health Unit that the water is safe to drink.");
                after.add("Once flood waters have receded you must not live in your home until the water supply has been declared safe for use, all flood-contaminated rooms have been thoroughly cleaned and disinfected, adequate toilet facilities are available, all electrical appliances and heating/cooling systems have been inspected, food, utensils and dishes have been examined, cleaned or disposed of, and floor drains and sumps have been cleaned and disinfected.");

                break;
            case "Cyclone":

                before.add("Prepare an emergency kit consisting of: Portable AM/FM radio and fresh batteries.");
                before.add("Torch, lamps, candles, matches, etc.");
                before.add("Water containers.");
                before.add("Canned food, can opener, stove with sufficient gas.");
                before.add("Rice, flour, biscuits, cheese, etc.");
                before.add("First aid kit and essential medicines.");
                before.add("Clothes secured in plastic bags.");
                before.add("Tool kit for emergency repairs (hammer, nail, rope, etc..)");

                during.add("During a cyclone warning class l: Make sure your emergency kit is ready.Monitor cyclone bulletins on Radio/TV.Prepare to secure windows and doors with shutters or shields.");
                during.add("During a cyclone warning Class ll: Verify that your emergency kit contains all essential items");
                during.add("Store sufficient amount of drinking waterContinue to monitor cyclone bulletins on Radio/TV.");
                during.add("Upon the issuance of a cyclone warning Class lll: Complete all preparatory measures. 1)Fix shutters., 2)Secure doors and windows., 3)Store loose articles.");
                during.add("Avoid areas prone to storm surges and flooding.");
                during.add("During a cyclone warning Class IV:");
                during.add("Stay inside. Seek shelter in the safest part of the house.Disconnect all electrical appliances.");
                during.add("Listen attentively to cyclone bulletins and advice on the Radio / TV.");
                during.add("If the house starts to suffer important damages, protect yourself with mattress, rugs or blankets.");

                after.add("Beware of the passage of the ‘EYE’. Do not assume that cyclonic conditions are over. The calm period is always followed by violent winds from the opposite direction.");
                after.add("Do not leave your shelter until the all-clear signals have been given by relevant Authorities.");
                after.add("Beware of fallen power lines, damaged buildings and trees and flooded water courses.Do not consume fallen fruits.");
                after.add("Boil water for drinking purposes.");

                break;
            case "Fire":

                listDataHeader.clear();
                listDataChild.clear();
                listDataHeader.add("On discovering a Fire");
                listDataHeader.add("On hearing the Alarm");
                listDataHeader.add("Porters and Security Staff");

                before.add("Raise the alarm by operating the nearest break glass call point.");
                before.add("Dial 8888 on the nearest internal telephone stating the location of the fire.");
                before.add("Attack the fire with nearest appropriate extinguisher if safe to do so and your escape route is clear.");

                during.add("Leave the building immediately, do not use lift, do not stop to collect personal belongings.");
                during.add("Close all doors and windows when leaving.");
                during.add("Report to your assembly point.");

                after.add("Assist orderly evacuation of building.");
                after.add("Prevent persons re-entering building during emergency.");
                after.add("Follow local fire procedure.");

                break;
            case "Thunderstorms":

                before.add("Check the weather forecast before leaving for extended periods outdoors.");
                before.add("Watch for signs of approaching storms.");
                before.add("If a storm is approaching, keep a NOAA Weather Radio or AM/FM radio with you.");
                before.add("Postpone outdoor activities if storms are imminent.");
                before.add("Check on neighbors who require special assistance: infants, the elderly, and people with disabilities.");

                during.add("Move to a sturdy building or car. Do Not take shelter in small sheds, under isolated trees, or in convertible automobiles.");
                during.add("If lightning occurs and sturdy shelter is not available, get inside a hard top automobile and keep the windows up.");
                during.add("Get out of boats and away from water.");
                during.add("Telephone lines and metal pipes can conduct electricity. Unplug appliances not necessary for obtaining weather information. Avoid using the telephone or any electrical appliances. Use phones only in an emergency.");
                during.add("Do not take a bath or shower.");
                during.add("Turn off air conditioners. Power can overload the compressors.");
                during.add("Get to higher ground if flash flooding or flooding is possible. Do Not attempt to drive to safety. Most flash flooding deaths occur in automobiles.");
                during.add("If you are caught outdoors and no shelter is nearby:");
                during.add("Find a low spot away from trees, fences, and poles. Make sure the place you pick is not subject to flooding.");
                during.add("If you are in the woods, take shelter under the shorter trees.");
                during.add("If you feel your skin tingle or your hair stand on end, squat low to the ground on the balls of your feet. Place your hands on your knees with your head between them. Make yourself the smallest target possible; minimize your contact with the ground.");

                after.add("Check on neighbors who may require special assistance: infants, the elderly, and people with disabilities.");
                after.add("Avoid all downed power lines. Assume that all have live electricity.");
                after.add("Continue to monitor NOAA Weather Radio and your local media for latest weather updates.");

                break;
            case "Draught":

                before.add("Never pour water down the drain when there may be another use for it. For example, use it to water your indoor plants or garden.");
                before.add("Repair dripping faucets by replacing washers. One drop per second wastes 2,700 gallons of water per year.");
                before.add("Check all plumbing for leaks and have any leaks repaired by a plumber.");
                before.add("Retrofit all household faucets by installing aerators with flow restrictors.");
                before.add("Install an instant hot water heater on your sink.");
                before.add("Insulate your water pipes to reduce heat loss and prevent them from breaking.");
                before.add("Choose appliances that are more energy and water efficient.");
                before.add("Start a compost pile as an alternate method of disposing of food waste or simply dispose of food in the garbage. (Kitchen sink disposals require a lot of water to operate properly).");

                during.add("Avoid flushing the toilet unnecessarily. Dispose of tissues, insects, and other similar waste in the trash rather than the toilet.");
                during.add("Avoid taking baths—take short showers—turn on water only to get wet and lather and then again to rinse off.");
                during.add("Avoid letting the water run while brushing your teeth, washing your face or shaving.");
                during.add("Place a bucket in the shower to catch excess water for watering plants.");
                during.add("Operate automatic dishwashers only when they are fully loaded. Use the light wash feature, if available, to use less water.");
                during.add("Clean vegetables in a pan filled with water rather than running water from the tap.");
                during.add("Operate automatic clothes washers only when they are fully loaded or set the water level for the size of your load.");
                during.add("Use a commercial car wash that recycles water.");
                during.add("Avoid over watering your lawn and water only when needed.");
                during.add("Avoid leaving sprinklers or hoses unattended. A garden hose can pour out 600 gallons or more in only a few hours.");
                during.add("Place a bucket in the shower to catch excess water for watering plants.");

                after.add("Use water wisely this time so that the chances of draught to occur are less.");
                after.add("Don't waste water for no reason");

                break;
        }
        listDataChild.put(listDataHeader.get(0), before);
        listDataChild.put(listDataHeader.get(1), during);
        listDataChild.put(listDataHeader.get(2), after);
    }
}
