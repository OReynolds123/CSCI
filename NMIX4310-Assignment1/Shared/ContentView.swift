//
//  ContentView.swift
//  Shared
//
//  Created by Owen Reynolds on 1/27/22.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        NavigationView {
            VStack(alignment: .center) {
                Group {
                    NavigationLink(destination: view1()){
                        btn(txt: "Scroll View", logo: "scroll")
                    }
                    NavigationLink(destination: view2()){
                        btn(txt: "SF Image", logo: "airplane")
                    }
                    NavigationLink(destination: view3()){
                        btn(txt: "Crazy Image", logo: "cloud")
                    }
                }
                .padding(.vertical, 1.0)
            }
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

struct btn: View {
    var txt: String
    var logo: String
    
    var body: some View {
        ZStack {
            Rectangle()
                .frame(width: 300, height: 70)
                .foregroundColor(Color(red: 150/255, green: 150/255, blue: 150/255))
                .cornerRadius(40)
            HStack {
                Image(systemName: logo)
                    .resizable()
                    .aspectRatio(contentMode: .fit)
                    .foregroundColor(Color.white)
                    .frame(width: 30, height: 30)
                    .padding(.horizontal, 25.0)
                Text(txt)
                    .font(.largeTitle)
                    .fontWeight(.semibold)
                    .foregroundColor(Color.white)
                Spacer()
            }
            .frame(width: 300, height: 70)
        }
    }
}
