//
//  view1.swift
//  Assignment 1
//
//  Created by Owen Reynolds on 1/27/22.
//

import SwiftUI

struct view1: View {
    var body: some View {
        VStack(alignment: .leading) {
            Text("My Personal Life")
                .font(.system(.largeTitle, design: .rounded))
                .fontWeight(.heavy)
            ScrollView(.horizontal) {
                HStack {
                    personalScroll(title:"My Family", image:"family", text:"This is my family! I have two loving parents, 2 brothers, and a sister. Before you ask, yes. I am a ferternal triplet.", textColor: .white, bgColor: Color(red: 130/255, green: 150/255, blue: 240/255), flip: false)
                    personalScroll(title:"My Dog", image:"kringle", text:"This is my family dog, Kringle! He's a yellow lab mix with some pit and boxer.", textColor: .white, bgColor: Color(red: 140/255, green: 140/255, blue: 140/255), flip: true)
                    personalScroll(title:"My Girlfriend", image:"emma", text:"This is a photo of my girlfriend, Emma, and I at a UGA football game! Go Dawgs!!", textColor: .black, bgColor: Color(red: 130/255, green: 240/255, blue: 150/255), flip: false)
                    personalScroll(title:"The Outdoors", image:"outdoors", text:"This is a photo of Emma and I doing what we love most: hiking the various North Georgia mountains.", textColor: .black, bgColor: Color(red: 230/255, green: 230/255, blue: 230/255), flip: true)
                }
            }
            Spacer()
        }
        .padding()
    }
}

struct view1_Previews: PreviewProvider {
    static var previews: some View {
        view1()
    }
}

struct personalScroll: View {
    var title: String
    var image: String
    var text: String
    var textColor: Color
    var bgColor: Color
    var flip: Bool
    
    var body: some View {
        if flip {
            VStack(alignment: .center) {
                VStack(alignment: .leading) {
                    Text(title)
                        .font(.system(.largeTitle, design: .rounded))
                        .fontWeight(.regular)
                        .foregroundColor(textColor)
                        .padding(.bottom, -15)
                        .padding([.top, .leading, .trailing])
                    Text(text)
                        .font(.system(.title2, design: .rounded))
                        .fontWeight(.light)
                        .foregroundColor(textColor)
                        .padding()
                }
                .frame(width:300, height: 200.0)
                .background(bgColor)
                Image(image)
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .frame(width:300, height: 200.0)
                
            }
            .frame(width: 300, height: 400)
            .background(bgColor)
            .cornerRadius(10)
        } else {
            VStack(alignment: .center) {
                Image(image)
                    .resizable()
                    .aspectRatio(contentMode: .fill)
                    .frame(width:300, height: 200.0)
                VStack(alignment: .leading) {
                    Text(title)
                        .font(.system(.largeTitle, design: .rounded))
                        .fontWeight(.regular)
                        .foregroundColor(textColor)
                        .padding(.bottom, -15)
                        .padding([.top, .leading, .trailing])
                    Text(text)
                        .font(.system(.title2, design: .rounded))
                        .fontWeight(.light)
                        .foregroundColor(textColor)
                        .padding()
                }
                .frame(width:300, height: 200.0)
                .background(bgColor)
                Spacer()
            }
            .frame(width: 300, height: 400)
            .background(bgColor)
            .cornerRadius(10)
        }
        
    }
}
