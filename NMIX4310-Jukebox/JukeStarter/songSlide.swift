//
//  songSlide.swift
//  JukeStarter
//
//  Created by Owen Reynolds on 4/5/22.
//

import SwiftUI

struct songSlide: View {
    var artist: String
    var songTitle: String
    var artistImage: String
        
    @State var c: CGFloat = 0.0
    
    var width = 250.0 - 20.0
    var width1 = 250.0 + 40.0
    
    var body: some View {
        VStack(alignment: .center) {
            Image(artistImage)
                .resizable()
                .cornerRadius(10)
                .frame(width: width + 20, height: width + 20)
            
            Text(songTitle)
                .font(.title2)
                .fontWeight(.semibold)
                .foregroundColor(Color.black)
                .lineLimit(1)
                .frame(width: width)
            
            Text(artist)
                .font(.headline)
                .fontWeight(.medium)
                .foregroundColor(Color.black.opacity(0.7))
                .lineLimit(1)
                .frame(width: width)
            
            VStack {
                ZStack(alignment: .leading) {
                    Rectangle()
                        .frame(width: .infinity , height: 5)
                        .foregroundColor(Color(UIColor.secondarySystemBackground))
                        .cornerRadius(10.0)
                    
                    Rectangle()
                        .frame(width: (((twist?.currentTime ?? 0.0) / (twist?.duration ?? 29.0)) * (width - 5)) + 0, height: 5)
                        .foregroundColor(Color.gray)
                        .cornerRadius(10.0)
                        .animation(.linear(duration: 0.1), value: twist?.currentTime ?? 0.0)
                    
                    Rectangle()
                        .frame(width: 10, height: 20 / 2)
                        .foregroundColor(Color.gray)
                        .cornerRadius(10)
                        .offset(x: (((twist?.currentTime ?? 0.0) / (twist?.duration ?? 29.0)) * (width - 15)) + 0, y: 0)
                        .animation(.linear(duration: 0.1), value: twist?.currentTime ?? 0.0)
                }
                .frame(width: width)
                
                HStack {
                    Text("\(printSecondsToHoursMinutesSeconds(Int(twist?.currentTime ?? 0.0)))")
                        .font(.headline)
                        .fontWeight(.regular)
                        .foregroundColor(Color.black)
                    
                    Spacer()
                    
                    Text("\(printSecondsToHoursMinutesSeconds(Int(twist?.duration ?? 29.0)))")
                        .font(.headline)
                        .fontWeight(.regular)
                        .foregroundColor(Color.black)
                }
                .offset(x:0, y:-5)
                .frame(width: width)
            }
        }
        .padding(20.0)
        .cornerRadius(10)
        .frame(width: width1)
        .overlay(
            RoundedRectangle(cornerRadius: 10)
                .stroke(Color.gray, lineWidth: 1)
        )
        .padding(.all)
    }
}

struct songSlide_Previews: PreviewProvider {
    static var previews: some View {
        songSlide(artist: "Zac Brown Band", songTitle: "Knee Deep", artistImage: "kneeDeep", c: 0.0)
    }
}
