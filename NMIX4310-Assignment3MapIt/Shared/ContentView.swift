//
//  ContentView.swift
//  Shared
//
//  Created by Owen Reynolds on 2/15/22.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        NavigationView {
            VStack(alignment: .leading) {
                Text("See The World")
                    .font(.largeTitle)
                    .fontWeight(.semibold)
                    .multilineTextAlignment(.center)
                    .padding(.leading, 20.0)
                loc(location: "Cloudland", lat: 34.836426, long: -85.480415)
                loc(location: "Providence", lat: 32.069083, long: -84.908830)
                loc(location: "Yonah", lat: 34.637670, long: -83.713287)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}


struct loc: View {
    var location: String
    var lat: Double
    var long: Double
        
    var body: some View {
        VStack(alignment: .center) {
            NavigationLink(destination: MapView(thelat: lat, thelong: long, latDelta: 0.01, lonDelta: 0.01, pickMap: 2)){
                btn(txt: location + " Standard", col: Color.green)
            }
            NavigationLink(destination: MapView(thelat: lat, thelong: long, latDelta: 0.002, lonDelta: 0.002, pickMap: 1)){
                btn(txt: location + " Satellite",  col: Color.blue)
            }
            NavigationLink(destination: MapView(thelat: lat, thelong: long, latDelta: 0.005, lonDelta: 0.005, pickMap: 0)){
                btn(txt: location + " Flyover",  col: Color.gray)
            }
        }
        .padding(.bottom)
    }
}
struct btn: View {
    var txt: String
    var col: Color
    
    var body: some View {
        ZStack {
            Rectangle()
                .frame(width: .infinity, height: 70)
                .foregroundColor(col)
                .cornerRadius(40)
                .padding(.horizontal)
            Text(txt)
                .font(.largeTitle)
                .fontWeight(.semibold)
                .foregroundColor(Color.white)
        }
    }
}
